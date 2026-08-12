@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.hapi.tag

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import dev.hybridlabs.hapi.CommonClass

object HAPIItemTags {
    val TUNA = create("tuna")
    val MACKEREL = create("mackerel")
    val HERRING = create("herring")
    val PUFFERFISH = create("pufferfish")
    val CARP = create("carp")
    val EEL = create("eel")
    val RAY = create("ray")
    val HAGFISH = create("hagfish")
    val GLOWSLIME_FISH = create("glowslime_fish")
    val SHRIMP = create("shrimp")
    val TENTACLE = create("tentacle")
    val UNI = create("uni")
    val FISH_MEAT = create("fish_meat")
    val FISH_STEAK = create("fish_steak")
    val LOBSTER_TAIL = create("lobster_tail")
    val LOBSTER_CLAW = create("lobster_claw")
    val LOBSTER_MEAT = create("lobster_meat")
    val CRAB_MEAT = create("crab_meat")
    val TUBE_SPONGE = create("tube_sponge")
    val BULL_KELP = create("bull_kelp")
    val CORAL_CHUNK = create("coral_chunk")
    val SHARK_TOOTH = create("shark_tooth")
    val GLOWSLIME = create("glowslime")
    val HAGSLIME = create("hagslime")
    val BRINE_BUCKET = create("brine_bucket")

    private fun create(id: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, CommonClass.locate(id))
    }

    private fun createConventional(id: String): TagKey<Item> {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", id))
    }
}
