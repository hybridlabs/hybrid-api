package dev.hybridlabs.hapi.entity.ai.goal.aquatic

import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.Goal

/**
 * Walks a land-dwelling mob to the nearest water when it is standing on dry ground.
 *
 * Reimplementation of the goal vanilla removed in 26.3.
 */
class TryFindWaterGoal(private val mob: PathfinderMob) : Goal() {
    override fun canUse(): Boolean {
        return mob.onGround() && !mob.level().getFluidState(mob.blockPosition()).`is`(FluidTags.WATER)
    }

    override fun start() {
        var waterPos: BlockPos? = null

        for (pos in BlockPos.betweenClosed(
            Mth.floor(mob.x - 2.0),
            Mth.floor(mob.y - 2.0),
            Mth.floor(mob.z - 2.0),
            Mth.floor(mob.x + 2.0),
            mob.blockY,
            Mth.floor(mob.z + 2.0)
        )) {
            if (mob.level().getFluidState(pos).`is`(FluidTags.WATER)) {
                waterPos = pos.immutable()
                break
            }
        }

        if (waterPos != null) {
            mob.moveControl.setWantedPosition(
                waterPos.x.toDouble(),
                waterPos.y.toDouble(),
                waterPos.z.toDouble(),
                1.0
            )
        }
    }
}
