package dev.hybridlabs.hapi.data.server.tag

import dev.hybridlabs.hapi.tag.HAPIItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagsProvider.ItemTagsProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {

        builder(HAPIItemTags.CORAL_CHUNK)
            .addOptional(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("hybrid_aquatic", "coral_chunk")))

        builder(HAPIItemTags.SHARK_TOOTH)
            .addOptional(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("hybrid_aquatic", "shark_tooth")))

        builder(HAPIItemTags.GLOWSLIME)
            .addOptional(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("hybrid_aquatic", "glowslime")))

        builder(HAPIItemTags.DRIFTWOOD_LOG)
            .addOptional(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("hybrid_aquatic", "driftwood_log")))

        builder(HAPIItemTags.DRIFTWOOD_PLANKS)
            .addOptional(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("hybrid_aquatic", "driftwood_planks")))

        builder(HAPIItemTags.DRIFTWOOD_SLAB)
            .addOptional(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("hybrid_aquatic", "driftwood_slab")))
    }
}