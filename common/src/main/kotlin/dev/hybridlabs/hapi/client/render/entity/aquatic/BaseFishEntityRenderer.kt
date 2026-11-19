package dev.hybridlabs.hapi.client.render.entity.aquatic

import com.mojang.datafixers.util.Either
import com.mojang.math.Axis
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.state.CameraRenderState
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animation.state.BoneSnapshot
import software.bernie.geckolib.cache.model.BakedGeoModel
import software.bernie.geckolib.cache.model.GeoBone
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.BoneSnapshots
import software.bernie.geckolib.renderer.base.GeoRenderState
import software.bernie.geckolib.renderer.base.RenderPassInfo
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer
import software.bernie.geckolib.renderer.layer.builtin.BlockAndItemGeoLayer
import kotlin.math.abs

@Suppress("LeakingThis")
open class BaseFishEntityRenderer<T : BaseFishEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
    canGlow: Boolean = false,
) : GeoEntityRenderer<T, R>(context, model) {

    val itemBone: String = "item"

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    init {
        if (canGlow) withRenderLayer(AutoGlowingGeoLayer(this))
        this.shadowRadius = 0.3f

        // Add some held item rendering
        withRenderLayer(object : BlockAndItemGeoLayer<T, Void, R>(this) {

            override fun addRenderData(
                animatable: T,
                relatedObject: Void?,
                renderState: R,
                partialTick: Float
            ) {
            }

            override fun getRelevantBones(renderState: R, model: BakedGeoModel): List<RenderData<R>> {
                return listOf(
                    RenderData(itemBone, ItemDisplayContext.GROUND) { _, state ->
                        Either.left(state.getGeckolibData(HAPIDataTickets.MAIN_HAND_ITEM) ?: ItemStack.EMPTY)
                    }
                )
            }

            override fun submitItemStackRender(
                poseStack: com.mojang.blaze3d.vertex.PoseStack,
                bone: GeoBone,
                stack: ItemStack,
                displayContext: ItemDisplayContext,
                renderState: R,
                renderTasks: SubmitNodeCollector,
                cameraState: CameraRenderState,
                packedLight: Int,
                packedOverlay: Int,
                renderColor: Int
            ) {
                rotateItem(poseStack, bone, stack, renderState)

                super.submitItemStackRender(
                    poseStack,
                    bone,
                    stack,
                    displayContext,
                    renderState,
                    renderTasks,
                    cameraState,
                    packedLight,
                    packedOverlay,
                    renderColor
                )
            }
        })
    }

    protected open fun rotateItem(
        poseStack: com.mojang.blaze3d.vertex.PoseStack,
        bone: GeoBone,
        stack: ItemStack,
        renderState: R
    ) {
        poseStack.mulPose(Axis.XP.rotationDegrees(-90f))
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 180f
    }

    override fun addRenderData(animatable: T, relatedObject: Void?, renderState: R, partialTick: Float) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        HAPIDataTickets.captureEntityState(animatable, renderState)

        renderState.addGeckolibData(HAPIDataTickets.IS_BABY, animatable.isBaby)
        renderState.addGeckolibData(HAPIDataTickets.MAIN_HAND_ITEM, animatable.mainHandItem)
        renderState.addGeckolibData(
            HAPIDataTickets.SIZE,
            if (variableSize) BaseWaterAnimal.getScaleAdjustment(animatable, 0.05f) else 1f
        )

        if (!animatable.isInWater && animatable.moistness < 580) {
            animatable.triggerAnim("Fish Controller", "misc.flop")
            return
        }

        renderState.addGeckolibData(
            HAPIDataTickets.TILT,
            Mth.clamp(Mth.lerp(partialTick, animatable.xRotO, animatable.xRot), -35f, 35f)
        )
        renderState.addGeckolibData(HAPIDataTickets.ROLL, turnRoll(animatable, partialTick))
    }

    override fun scaleModelForRender(renderPassInfo: RenderPassInfo<R>, widthScale: Float, heightScale: Float) {
        val size = renderPassInfo.getGeckolibData(HAPIDataTickets.SIZE) ?: 1f
        super.scaleModelForRender(renderPassInfo, widthScale * size, heightScale * size)
    }

    override fun adjustModelBonesForRender(renderPassInfo: RenderPassInfo<R>, snapshots: BoneSnapshots) {
        val tilt = renderPassInfo.getGeckolibData(HAPIDataTickets.TILT) ?: return
        val roll = renderPassInfo.getGeckolibData(HAPIDataTickets.ROLL) ?: return

        snapshots.get(PartNames.BODY).ifPresent { bone: BoneSnapshot ->
            bone.setRotX(tilt * -Mth.DEG_TO_RAD)
        }
        snapshots.get("torso").ifPresent { bone: BoneSnapshot ->
            bone.setRotZ(roll * -Mth.DEG_TO_RAD)
        }
        snapshots.get("torso_2").ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * Mth.DEG_TO_RAD)
        }
        snapshots.get(PartNames.TAIL).ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * Mth.DEG_TO_RAD)
        }
        snapshots.get(PartNames.TAIL_FIN).ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * Mth.DEG_TO_RAD)
        }
    }

    companion object {
        /** Smoothed turn roll for the current render pass, updating the entity's roll state. */
        fun turnRoll(animatable: BaseFishEntity, partialTick: Float): Float {
            val yawDiff = animatable.yRot - animatable.yRotO
            val targetRoll = Mth.clamp(yawDiff * 3f, -30f, 30f)

            val turnSpeed = abs(yawDiff)
            val smoothing = Mth.clamp(0.05f + turnSpeed * 0.02f, 0.05f, 0.25f)
            animatable.currentRoll = Mth.lerp(smoothing, animatable.currentRoll, targetRoll)

            return Mth.lerp(partialTick, animatable.prevRoll, animatable.currentRoll)
        }
    }
}
