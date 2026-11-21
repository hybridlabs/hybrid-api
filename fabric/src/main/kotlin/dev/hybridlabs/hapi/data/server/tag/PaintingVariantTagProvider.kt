package dev.hybridlabs.hapi.data.server.tag

import dev.hybridlabs.hapi.tag.HAPIPaintingTags
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.decoration.painting.PaintingVariant
import java.util.concurrent.CompletableFuture

class PaintingVariantTagProvider (output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagsProvider<PaintingVariant>(output, Registries.PAINTING_VARIANT, registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
        builder(HAPIPaintingTags.KEEPS_PAINTING_VARIANT)

        builder(HAPIPaintingTags.TRANSPARENT_PAINTING)
    }
}