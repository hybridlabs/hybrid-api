package dev.hybridlabs.hapi.item

import dev.hybridlabs.hapi.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.Item
import java.util.function.Supplier

object HAPIItems {

    val CREATURE_NET = register("creature_net") { CreatureNetItem(it.stacksTo(1)) }

    /**
     * Registers an item, pre-filling the properties with the item's registry key, which vanilla
     * requires before an item can be constructed.
     */
    fun register(id: String, factory: (Item.Properties) -> Item): Supplier<Item> {
        return CommonClass.ITEMS.register(id) {
            factory(Item.Properties().setId(ResourceKey.create(Registries.ITEM, CommonClass.locate(id))))
        }
    }
}
