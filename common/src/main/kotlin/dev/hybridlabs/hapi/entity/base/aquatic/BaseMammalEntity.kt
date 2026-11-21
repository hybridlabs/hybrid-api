package dev.hybridlabs.hapi.entity.base.aquatic

import dev.hybridlabs.hapi.entity.ai.control.aquatic.FloatControl
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.TryFindWaterGoal
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalFollowParentGoal
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.FluidTags
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.PathType
import com.geckolib.animatable.instance.AnimatableInstanceCache
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.state.AnimationTest
import com.geckolib.animation.RawAnimation
import com.geckolib.constant.DefaultAnimations
import com.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis", "UNUSED_PARAMETER", "unused")
open class BaseMammalEntity(type: EntityType<out BaseMammalEntity>, world: Level
) :
    BaseWaterAnimal(type, world) {
    private val factory = GeckoLibUtil.createInstanceCache(this)

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(PathType.WATER, 0.0f)
        setPathfindingMalus(PathType.FIRE_IN_NEIGHBOR, 16.0f)
        setPathfindingMalus(PathType.FIRE, -1.0f)

        moveControl = FloatControl(this)

        return AmphibiousPathNavigation(this, level)
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: EntitySpawnReason,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        this.airSupply = this.maxAirSupply
        this.setYRot(0.0f)
        if (this.random.nextFloat() < 0.25f) {
            this.setAge(-6000)
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                 "Walk/Swim/Idle", 4
            ) { state: AnimationTest<BaseMammalEntity> ->
                when {
                    state.isMoving && onGround() -> state.setAndContinue(DefaultAnimations.WALK)
                    state.isMoving && isInWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !state.isMoving && isInWater -> state.setAndContinue(WATER_IDLE)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    init {
        setPathfindingMalus(PathType.WATER, 0.0f)
        moveControl = FloatControl(this)
        lookControl = LookControl(this)
        navigation = AmphibiousPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.5, 2))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(4, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        goalSelector.addGoal(5, TryFindWaterGoal(this))
        goalSelector.addGoal(5, WaterAnimalFollowParentGoal(this, 1.1))
        goalSelector.addGoal(6, MeleeAttackGoal(this, 1.2000000476837158, true))
    }

    override fun aiStep() {
        super.aiStep()
        val vec3d = this.getDeltaMovement()
        if (!this.onGround() && this.isSwimming && vec3d.y < 0.0) {
            this.setDeltaMovement( vec3d.multiply(1.0, 0.6, 1.0))
        }
    }

    companion object {
        val WATER_IDLE: RawAnimation = RawAnimation.begin().thenPlay("misc.water_idle")

        fun canSpawn(
            type: EntityType<out BaseMammalEntity>,
            level: LevelAccessor,
            spawnReason: EntitySpawnReason,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            val mutable = pos.mutable()
            do {
                mutable.move(Direction.UP)
            } while (level.getFluidState(mutable).`is`(FluidTags.WATER))
            return level.getBlockState(mutable).isAir
        }
    }
}
