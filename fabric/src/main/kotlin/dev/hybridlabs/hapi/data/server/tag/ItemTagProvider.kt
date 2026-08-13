package dev.hybridlabs.hapi.data.server.tag

import dev.hybridlabs.hapi.tag.HAPIItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceLocation
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {

        getOrCreateTagBuilder(HAPIItemTags.CORAL_CHUNK)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "coral_chunk"))

        getOrCreateTagBuilder(HAPIItemTags.SHARK_TOOTH)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "shark_tooth"))

        getOrCreateTagBuilder(HAPIItemTags.GLOWSLIME)
            .addOptional(ResourceLocation.fromNamespaceAndPath("hybrid_aquatic", "glowslime"))
    }
}