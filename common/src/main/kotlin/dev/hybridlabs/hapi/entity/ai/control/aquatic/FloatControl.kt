package dev.hybridlabs.hapi.entity.ai.control.aquatic

import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.phys.Vec3
import kotlin.math.max

class FloatControl(mob: BaseWaterAnimal) : MoveControl(mob) {
    override fun tick() {
        var deltaMovement: Vec3 = this.mob.getDeltaMovement()
        if ((this.mob as BaseWaterAnimal).isBelowWaterline()) {
            this.mob.setDeltaMovement( deltaMovement.add(0.0, 0.025, 0.0))
            if (this.mob.isUnderWater)
                deltaMovement = this.mob.getDeltaMovement()
                this.mob.setDeltaMovement( deltaMovement.add(0.0, 0.025, 0.0))
            deltaMovement = this.mob.getDeltaMovement()
            this.mob.setDeltaMovement(deltaMovement.x, max(deltaMovement.y, 0.0), deltaMovement.z)
        }
        super.tick()
    }
}