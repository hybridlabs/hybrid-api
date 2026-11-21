package dev.hybridlabs.hapi.entity.ai.control.flying

import dev.hybridlabs.hapi.entity.base.skies.BaseFlyingAnimal
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.phys.Vec3
import kotlin.math.max

// credit to fowl play for the code

class BirdFloatControl(bird: BaseFlyingAnimal) : MoveControl<BaseFlyingAnimal>(bird) {
    override fun tick() {
        var deltaMovement: Vec3 = this.mob.getDeltaMovement()
        if ((this.mob as BaseFlyingAnimal).isBelowWaterline()) {
            this.mob.setDeltaMovement( deltaMovement.add(0.0, 0.05, 0.0))
            if (this.mob.isUnderWater)
                deltaMovement = this.mob.getDeltaMovement()
                this.mob.setDeltaMovement( deltaMovement.add(0.0, 0.05, 0.0))
            deltaMovement = this.mob.getDeltaMovement()
            this.mob.setDeltaMovement(deltaMovement.x, max(deltaMovement.y, 0.0), deltaMovement.z)
        }
        super.tick()
    }
}
