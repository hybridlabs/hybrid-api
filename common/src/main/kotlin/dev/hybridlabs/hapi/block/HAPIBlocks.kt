package dev.hybridlabs.hapi.block

import dev.hybridlabs.hapi.platform.registration.RegistrationProvider
import dev.hybridlabs.hapi.platform.registration.RegistryObject
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block

object HAPIBlocks {
    /**
     * Registers a block, pre-filling the properties' registry key, which vanilla requires before
     * a block can be constructed.
     */
    fun register(
        provider: RegistrationProvider<Block>,
        id: String,
        factory: () -> Block
    ): RegistryObject<Block> {
        val key = ResourceKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath(provider.modId, id)
        )
        return provider.register(id) {
            HAPIBlockProperties.withId(key, factory)
        }
    }
}
