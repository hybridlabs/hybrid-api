package dev.hybridlabs.hapi.data.server.tag

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.Instrument
import java.util.concurrent.CompletableFuture

class InstrumentTagProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagsProvider<Instrument>(output, Registries.INSTRUMENT ,registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
    }
}