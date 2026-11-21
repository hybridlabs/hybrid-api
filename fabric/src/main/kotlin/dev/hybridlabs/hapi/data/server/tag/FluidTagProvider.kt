package dev.hybridlabs.hapi.data.server.tag

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class FluidTagProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>):
    FabricTagsProvider.FluidTagsProvider(output, registriesFuture) {

    override fun addTags(p0: HolderLookup.Provider) {
    }
}