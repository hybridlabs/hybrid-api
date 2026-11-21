package dev.hybridlabs.hapi.entity.base.skies

import net.minecraft.sounds.SoundEvents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.ItemTags
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.PlayerRideableJumping
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import com.geckolib.animatable.GeoEntity
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.animation.AnimationController
import com.geckolib.animation.state.AnimationTest
import com.geckolib.constant.DefaultAnimations

@Suppress("LeakingThis", "DEPRECATION")
open class BaseRatiteEntity(
    type: EntityType<out BaseRatiteEntity>,
    world: Level,
    private val isRideable: Boolean,
) :
    BaseFlyingAnimal(type, world), PlayerRideableJumping,
    GeoEntity {

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(
                 "Walk/Fly/Idle", 4
            ) { state: AnimationTest<BaseRatiteEntity> ->
                when {
                    state.isMoving && onGround() -> state.setAndContinue(DefaultAnimations.WALK)
                    !this.onGround() -> state.setAndContinue(DefaultAnimations.FLY)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }
    //#endregion

    override fun aiStep() {
        super.aiStep()
        val vec3d = this.getDeltaMovement()
        if (!this.onGround() && vec3d.y < 0.0) {
            this.setDeltaMovement( vec3d.multiply(1.0, 0.75, 1.0))
        }
    }

    //#region Jumping
    override fun canJump(): Boolean {
        return isRideable
    }

    override fun onPlayerJump(power: Int) {
        if (!isRideable) return
        if (!this.onGround()) return

        val jumpStrength = power / 100.0f
        val verticalBoost = 0.4 + (jumpStrength * 0.6)

        this.setDeltaMovement( this.getDeltaMovement().add(0.0, verticalBoost, 0.0))
        this.needsSync = true
    }

    override fun handleStartJump(power: Int) {
        if (!isRideable) return
        if (canJump()) {
            this.playSound(SoundEvents.CAMEL_DASH, 1.0f, 1.0f)
            this.onPlayerJump(power)
        }
    }

    override fun handleStopJump() {
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val stack = player.getItemInHand(hand)

        if (isFood(stack)) {
            return super.mobInteract(player, hand)
        }

        if (isRideable && !isBaby && !isVehicle && !player.isSecondaryUseActive) {
            if (!level().isClientSide()) {
                player.startRiding(this)
            }
            return if (level().isClientSide()) InteractionResult.SUCCESS else InteractionResult.SUCCESS_SERVER
        }

        return super.mobInteract(player, hand)
    }

    override fun getControllingPassenger(): LivingEntity? {
        if (!isRideable) return null
        return if (firstPassenger is Player) firstPassenger as Player else null
    }

    override fun tickRidden(player: Player, travelVector: Vec3) {
        if (!isRideable) {
            super.tickRidden(player, travelVector)
            return
        }
        super.tickRidden(player, travelVector)
        this.setRot(player.yRot, player.xRot * 0.5f)
        this.yHeadRot = this.getYRot()
        this.yBodyRot = this.yHeadRot
        this.yRotO = this.yBodyRot
    }

    override fun travel(travelVector: Vec3) {
        if (isRideable && isAlive && isVehicle) {
            val passenger = controllingPassenger
            if (passenger is Player) {

                yRot = passenger.yRot
                xRot = passenger.xRot * 0.5f
                yRotO = yRot
                yHeadRot = yRot

                val forward = passenger.zza
                val strafe = passenger.xxa

                val speed = getAttributeValue(Attributes.MOVEMENT_SPEED).toFloat()

                setSpeed(speed)
                super.travel(Vec3(strafe.toDouble(), travelVector.y, forward.toDouble()))
                return
            }
        }

        super.travel(travelVector)
    }

    override fun getRiddenInput(player: Player, travelVector: Vec3): Vec3 {
        return if (isRideable) Vec3(0.0, 0.0, 1.0) else super.getRiddenInput(player, travelVector)
    }

    override fun getRiddenSpeed(player: Player): Float {
        return if (isRideable) getAttributeValue(Attributes.MOVEMENT_SPEED).toFloat()
        else super.getRiddenSpeed(player)
    }
    //#endregion

    companion object {
        @Suppress("UNUSED_PARAMETER")

        val BREEDING_INGREDIENT: Ingredient by lazy {
            // See BaseSharkEntity: bootstrap registry lookups cannot be acquired after the
            // registries freeze, which already happened by the time NeoForge constructs mods.
            Ingredient.of(BuiltInRegistries.ITEM.getOrThrow(ItemTags.VILLAGER_PLANTABLE_SEEDS))
        }
    }
}
