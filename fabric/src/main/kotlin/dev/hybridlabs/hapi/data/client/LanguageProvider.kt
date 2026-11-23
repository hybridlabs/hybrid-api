package dev.hybridlabs.hapi.data.client

import dev.hybridlabs.hapi.item.HAPIItems
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class LanguageProvider( output: FabricPackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) : FabricLanguageProvider(output,lookupProvider) {
    override fun generateTranslations(lookupProvider: HolderLookup.Provider, builder: TranslationBuilder) {
        val creatureNetKey = HAPIItems.CREATURE_NET.get().descriptionId

        // Item group and item functions
        builder.addAll(
            mapOf(
                creatureNetKey to "Creature Net",
                "$creatureNetKey.description" to "Stored Entity: %s",
                "$creatureNetKey.function" to "Lets you catch and move aquatic creatures",
                "$creatureNetKey.properties" to "Placed creatures become passive and don't despawn",
            )
        )

        // Item tags are shown by recipe viewers, so translate them the same way as items.
        builder.addAll(
            mapOf(
                "tag.item.hapi.coral_chunk" to "Coral Chunks",
                "tag.item.hapi.shark_tooth" to "Shark Teeth",
                "tag.item.hapi.glowslime" to "Glowslime",
                "tag.item.hapi.seashell_tool_materials" to "Seashell Tool Materials",
                "tag.item.hapi.repairs_seashell_armor" to "Repairs Seashell Armor",
                "tag.item.hapi.repairs_moon_jelly_armor" to "Repairs Moon Jelly Armor",
                "tag.item.hapi.driftwood_log" to "Driftwood Logs",
                "tag.item.hapi.driftwood_planks" to "Driftwood Planks",
                "tag.item.hapi.driftwood_slab" to "Driftwood Slabs",
            )
        )
    }

    private fun TranslationBuilder.addAll(translations: Map<String, String>) {
        translations.forEach { (key, translation) -> add(key, translation) }
    }
}
