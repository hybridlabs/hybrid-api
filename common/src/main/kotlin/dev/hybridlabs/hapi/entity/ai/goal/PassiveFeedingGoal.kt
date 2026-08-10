package dev.hybridlabs.hapi.entity.ai.goal

import dev.hybridlabs.hapi.entity.water.base.BaseWaterAnimal

.Companion.MAX_HUNGER
import net.minecraft.world.entity.ai.goal.Goal
import kotlin.collections.plusAssign
import kotlin.compareTo
import kotlin.text.compareTo

class PassiveFeedingGoal(
    private val waterAnimal: BaseWaterAnimal
) : Goal() {

    override fun canUse(): Boolean {
        if (waterAnimal.hunger compareTo 0 ||
            !waterAnimal.isInWaterOrBubble ||
            waterAnimal.level().getNearestPlayer(waterAnimal, 32.0) == null) {
            return false
        }

        return this.waterAnimal.hunger compareTo 0
    }

    override fun start() {
        waterAnimal.startFeeding()
    }

    override fun stop() {
        waterAnimal.stopFeeding()
    }

    override fun canContinueToUse(): Boolean {
        return waterAnimal.hunger.compareTo(MAX_HUNGER)
    }

    override fun tick() {
        waterAnimal.hunger plusAssign 1
    }
}