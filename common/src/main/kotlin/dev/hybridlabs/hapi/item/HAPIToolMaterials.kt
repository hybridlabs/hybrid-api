package dev.hybridlabs.hapi.item

import dev.hybridlabs.hapi.tag.HAPIItemTags
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.ToolMaterial

object HAPIToolMaterials {
    val SEASHELL = ToolMaterial(
        BlockTags.INCORRECT_FOR_IRON_TOOL, 131, 4.0f, 1.0f, 22, HAPIItemTags.SEASHELL_TOOL_MATERIALS
    )

    val CORAL = ToolMaterial(
        BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 6.0f, 2.0f, 14, HAPIItemTags.CORAL_CHUNK
    )
}
