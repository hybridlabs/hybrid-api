package dev.hybridlabs.hapi.tag

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.block.Block
import dev.hybridlabs.hapi.CommonClass

object HAPIBlockTags {

    val PLUSHIES = create("plushies")

    private fun create(id: String): TagKey<Block> {
        return TagKey.create(Registries.BLOCK, CommonClass.locate(id))
    }
}
