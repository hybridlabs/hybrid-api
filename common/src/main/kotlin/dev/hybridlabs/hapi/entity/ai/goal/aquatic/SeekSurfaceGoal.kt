package dev.hybridlabs.hapi.entity.ai.goal.aquatic

import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.LevelReader
import net.minecraft.world.phys.Vec3
import java.util.*

class SeekSurfaceGoal(private val mob: BaseWaterAnimal) : Goal() {
    init {
        this.flags = EnumSet.of<Flag>(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        return this.mob.seekSurfaceCooldown < 150
    }

    override fun canContinueToUse(): Boolean {
        return this.canUse()
    }

    override fun isInterruptable(): Boolean {
        return false
    }

    override fun start() {
        this.findAirPosition()
    }

    private fun findAirPosition() {
        val iterable = BlockPos.betweenClosed(
            Mth.floor(this.mob.getX() - 1.0),
            this.mob.blockY,
            Mth.floor(this.mob.getZ() - 1.0),
            Mth.floor(this.mob.getX() + 1.0),
            Mth.floor(this.mob.getY() + 8.0),
            Mth.floor(this.mob.getZ() + 1.0)
        )
        var blockpos: BlockPos? = null

        for (blockpos1 in iterable) {
            if (this.givesAir(this.mob.level(), blockpos1)) {
                blockpos = blockpos1
                break
            }
        }

        if (blockpos == null) {
            blockpos = BlockPos.containing(this.mob.getX(), this.mob.getY() + 8.0, this.mob.getZ())
        }

        this.mob.getNavigation()
            .moveTo(blockpos.x.toDouble(), (blockpos.y + 1).toDouble(), blockpos.z.toDouble(), 1.0)
    }

    override fun tick() {
        this.findAirPosition()
        this.mob.moveRelative(0.02f, Vec3(this.mob.xxa.toDouble(), this.mob.yya.toDouble(), this.mob.zza.toDouble()))
        this.mob.move(MoverType.SELF, this.mob.getDeltaMovement())
    }

    private fun givesAir(level: LevelReader, pos: BlockPos): Boolean {
        return (level.getFluidState(pos).isEmpty
        )
    }
}
