package dev.hybridlabs.hapi.config

import com.mojang.serialization.Codec
import dev.hybridlabs.hapi.Constants
import dev.hybridlabs.hapi.platform.Services
import org.slf4j.Logger
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.notExists

/**
 * Creates and loads the JSON config files shared by the Hybrid Labs mods.
 *
 * A config is a data class with a [Codec]. A minimal setup only needs the mod id, the codec and a default value:
 *
 * ```kotlin
 * val handler = ConfigHelper.initializeConfig(
 *     modId = Constants.MOD_ID,
 *     codec = MyConfig.CODEC,
 *     defaultConfig = MyConfig(),
 *     dataVersion = MyConfig::dataVersion,
 * )
 * ```
 */
object ConfigHelper {
    /**
     * Resolves a config file in the platform config directory.
     *
     * @param modId the mod id used for the default file name
     * @param fileName the file name, defaults to `<modId>.json`
     */
    fun configFile(modId: String, fileName: String = "$modId.json"): Path =
        Services.PLATFORM.getConfigDir().resolve(fileName)

    /**
     * Creates and loads a config in the platform config directory.
     *
     * @param T the config type
     * @param modId the mod id, also used for the file name and as the default log name
     * @param codec the codec used to serialize and deserialize the config
     * @param defaultConfig the config used when the file is missing or cannot be read
     * @param dataVersion reads the data version of a config; increase it in the mod to reset old configs
     * @param modName the mod name used in log messages
     * @param logger the logger used for diagnostics
     */
    fun <T : Any> initializeConfig(
        modId: String,
        codec: Codec<T>,
        defaultConfig: T,
        dataVersion: (T) -> Int = { 0 },
        modName: String = modId,
        logger: Logger = Constants.LOGGER,
    ): ConfigHandler<T> = initializeConfig(
        configFile(modId), codec, defaultConfig, dataVersion, modName, logger
    )

    /**
     * Creates and loads a config from an explicit file.
     *
     * Use this when the config does not live in the platform config directory, or when the file name does not
     * follow the `<modId>.json` convention.
     *
     * @param T the config type
     * @param file the config file
     * @param codec the codec used to serialize and deserialize the config
     * @param defaultConfig the config used when the file is missing or cannot be read
     * @param dataVersion reads the data version of a config; increase it in the mod to reset old configs
     * @param modName the mod name used in log messages
     * @param logger the logger used for diagnostics
     */
    fun <T : Any> initializeConfig(
        file: Path,
        codec: Codec<T>,
        defaultConfig: T,
        dataVersion: (T) -> Int = { 0 },
        modName: String = Constants.MOD_NAME,
        logger: Logger = Constants.LOGGER,
    ): ConfigHandler<T> {
        file.parent?.let { Files.createDirectories(it) }

        val handler = ConfigHandler(file.toFile(), codec, defaultConfig, dataVersion, logger)

        if (file.notExists()) {
            logger.info("{} config file did not exist, creating one", modName)
            handler.save()
            return handler
        }

        logger.info("Loading {} config file", modName)
        if (!handler.load()) {
            logger.warn("{} config file could not be read, backing it up and resetting to defaults", modName)
            reset(handler, modName, logger)
            return handler
        }

        if (handler.isOutdated()) {
            logger.info("Old {} config file found, upgrading", modName)
            reset(handler, modName, logger)
        }

        return handler
    }

    private fun <T : Any> reset(handler: ConfigHandler<T>, modName: String, logger: Logger) {
        handler.backup()
        handler.config = handler.defaultConfig
        handler.save()
        logger.info("{} config reset, the old config has been backed up to \"{}\"", modName, handler.backupFile)
    }
}
