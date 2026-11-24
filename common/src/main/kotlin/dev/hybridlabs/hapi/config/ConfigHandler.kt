package dev.hybridlabs.hapi.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.mojang.datafixers.util.Pair
import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.JsonOps
import dev.hybridlabs.hapi.Constants
import org.slf4j.Logger
import java.io.File

/**
 * Reads and writes a JSON config file backed by a [Codec].
 *
 * The loaded config is kept in [config]. Use [ConfigHelper.initializeConfig] instead of constructing this directly
 * if the file may be missing, malformed or from an older version.
 *
 * @param T the config type
 * @property file the file the config is stored in
 * @property codec the codec used to serialize and deserialize the config
 * @property defaultConfig the config used when the file is missing or cannot be read
 * @property dataVersion reads the data version of a config; increase it in the mod to reset old configs
 * @property logger the logger used for diagnostics
 */
class ConfigHandler<T : Any>(
    val file: File,
    val codec: Codec<T>,
    val defaultConfig: T,
    val dataVersion: (T) -> Int = { 0 },
    private val logger: Logger = Constants.LOGGER,
) {
    val backupFile: File = File(file.absoluteFile.parentFile, "${file.name}-backup")

    var config: T = defaultConfig

    /**
     * Reads the config from the config file.
     * @return a data result of the config
     */
    fun read(): DataResult<Pair<T, JsonElement>> {
        val json = file.reader().use(JsonParser::parseReader)
        return codec.decode(JsonOps.INSTANCE, json)
    }

    /**
     * Reads and saves to memory the current config from its file.
     * @return whether the config was loaded successfully
     */
    fun load(): Boolean {
        return runCatching {
            val result = read().result()

            if (!result.isPresent) {
                logger.warn("Failed to decode {}; the file may be malformed or from an older version", file.name)
                return false
            }

            config = result.get().first
            true
        }.getOrElse { exception ->
            logger.warn("Failed to read {}", file.name, exception)
            false
        }
    }

    /**
     * Writes [config] to the config file.
     * @return whether the config was saved successfully
     */
    fun save(): Boolean {
        return runCatching {
            val result = codec.encodeStart(JsonOps.INSTANCE, config).result()

            if (!result.isPresent) {
                return false
            }

            file.parentFile?.mkdirs()
            file.writeText(GSON.toJson(result.get()))
            true
        }.getOrElse { exception ->
            logger.error("Failed to save {}", file.name, exception)
            false
        }
    }

    /**
     * Copies the config file to [backupFile].
     * @return whether the backup was successful
     */
    fun backup(): Boolean {
        return runCatching {
            backupFile.writeBytes(file.readBytes())
        }.isSuccess
    }

    /**
     * Whether the loaded config is older than [defaultConfig].
     */
    fun isOutdated(): Boolean = dataVersion(config) < dataVersion(defaultConfig)

    companion object {
        val GSON: Gson = GsonBuilder().setPrettyPrinting().create()
    }
}
