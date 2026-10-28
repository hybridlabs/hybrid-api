package dev.hybridlabs.hapi.entity.ai.goal.skies

import dev.hybridlabs.hapi.entity.base.skies.BaseFlyingAnimal
import net.minecraft.world.entity.ai.goal.Goal

class FlyingAnimalWalkGoal(
    private val bird: BaseFlyingAnimal,
) : Goal() {

    override fun canUse(): Boolean {
        TODO("Not yet implemented")
    }
}
