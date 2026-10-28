package dev.hybridlabs.hapi.entity.ai.goal.skies

import dev.hybridlabs.hapi.entity.base.skies.BaseFlyingAnimal
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal

class FlyingAnimalRandomFlyingGoal(
    private val bird: BaseFlyingAnimal
    ) : WaterAvoidingRandomFlyingGoal(
    bird,
        1.0
    ) {

    override fun canUse(): Boolean {
        if (bird.isClipped) return false
        return super.canUse()
    }

    override fun canContinueToUse(): Boolean {
        if (bird.isClipped) return false
        return super.canContinueToUse()
    }
}
