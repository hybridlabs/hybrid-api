package dev.hybridlabs.hapi.data.server.tag

import dev.hybridlabs.hapi.tag.HAPIBlockTags
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagsProvider.BlockTagsProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {

        builder(HAPIBlockTags.DRIFTWOOD_LOG)
            .addOptional(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("hybrid_aquatic", "driftwood_log")))

        builder(HAPIBlockTags.DRIFTWOOD_PLANKS)
            .addOptional(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("hybrid_aquatic", "driftwood_planks")))

        builder(HAPIBlockTags.DRIFTWOOD_SLAB)
            .addOptional(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("hybrid_aquatic", "driftwood_slab")))
    }
}
