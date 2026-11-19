package dev.hybridlabs.hapi.data.server.tag

import dev.hybridlabs.hapi.tag.HAPIPaintingTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.decoration.painting.PaintingVariant
import java.util.concurrent.CompletableFuture

class PaintingVariantTagProvider (output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<PaintingVariant>(output, Registries.PAINTING_VARIANT, registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
        builder(HAPIPaintingTags.KEEPS_PAINTING_VARIANT)

        builder(HAPIPaintingTags.TRANSPARENT_PAINTING)
    }
}