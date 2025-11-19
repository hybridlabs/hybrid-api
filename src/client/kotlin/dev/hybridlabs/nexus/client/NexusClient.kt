package dev.hybridlabs.nexus.client

import dev.hybridlabs.nexus.Nexus
import net.fabricmc.api.ClientModInitializer
import org.slf4j.LoggerFactory

object NexusClient : ClientModInitializer {
    private val logger = LoggerFactory.getLogger("${Nexus.MOD_ID}-client")

    override fun onInitializeClient() {
        logger.info("Initializing ${Nexus.MOD_NAME} (Client)")
    }
}
