package dev.hybridlabs.hapi.client.item.tooltip

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

/**
 * An item tooltip that is only appended if [itemPredicate] is passed.
 */
abstract class PredicateItemTooltipCallback(
    /**
     * The item predicate.
     */
    private val itemPredicate: (ItemStack) -> Boolean
) : ItemTooltipCallback {
    /**
     * A secondary constructor for matching a set of items.
     */
    constructor(vararg items: Item) : this({ stack -> items.any { stack.`is`(it) } })

    override fun getTooltip(
        stack: ItemStack,
        toolTipContext: Item.TooltipContext,
        context: TooltipFlag,
        lines: MutableList<Component>
    ) {
        if (itemPredicate(stack)) {
            appendTooltip(stack, context, lines)
        }
    }

    abstract fun appendTooltip(stack: ItemStack, context: TooltipFlag, lines: MutableList<Component>)
}
