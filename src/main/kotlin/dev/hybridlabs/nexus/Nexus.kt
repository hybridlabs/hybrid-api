package dev.hybridlabs.nexus

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object Nexus : ModInitializer {
    const val MOD_ID = "nexus"
    const val MOD_NAME = "Nexus"

    private val logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")
    }
}
