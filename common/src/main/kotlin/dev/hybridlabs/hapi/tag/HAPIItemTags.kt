@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.hapi.tag

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import dev.hybridlabs.hapi.CommonClass

object HAPIItemTags {
    val CORAL_CHUNK = create("coral_chunk")
    val SHARK_TOOTH = create("shark_tooth")
    val GLOWSLIME = create("glowslime")

    private fun create(id: String): TagKey<Item> {
        return TagKey.create(Registries.ITEM, CommonClass.locate(id))
    }

    private fun createConventional(id: String): TagKey<Item> {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("c", id))
    }
}
