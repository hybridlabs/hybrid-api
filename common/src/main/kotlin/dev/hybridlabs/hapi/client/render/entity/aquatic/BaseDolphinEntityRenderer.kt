package dev.hybridlabs.hapi.client.render.entity.aquatic

import com.mojang.math.Axis
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseDolphinEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.client.model.geom.PartNames
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import com.geckolib.animation.state.BoneSnapshot
import com.geckolib.cache.model.GeoBone
import com.geckolib.model.GeoModel
import com.geckolib.renderer.GeoEntityRenderer
import com.geckolib.renderer.base.BoneSnapshots
import com.geckolib.renderer.base.GeoRenderState
import com.geckolib.renderer.base.RenderPassInfo
import com.geckolib.renderer.layer.builtin.BlockAndItemGeoLayer

open class BaseDolphinEntityRenderer<T : BaseDolphinEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false
) : GeoEntityRenderer<T, R>(context, model) {

    private val itemBone: String = "item"

    init {
        withRenderLayer(object : BlockAndItemGeoLayer<T, Void, R>(context, this) {

            override fun getRelevantBones(
                animatable: T,
                relatedObject: Void?,
                renderState: R,
                partialTick: Float
            ): List<BlockAndItemGeoLayer.RenderData> {
                val stack = renderState.getGeckolibData(HAPIDataTickets.MAIN_HAND_ITEM) ?: return emptyList()
                if (stack.isEmpty) return emptyList()

                return listOf(
                    BlockAndItemGeoLayer.RenderData.item(
                        itemBone,
                        ItemDisplayContext.GROUND,
                        com.geckolib.util.RenderUtil.createRenderStateForItem(
                            stack, itemModelResolver, ItemDisplayContext.GROUND, animatable
                        )
                    )
                )
            }

            override fun addRenderData(
                animatable: T,
                relatedObject: Void?,
                renderState: R,
                partialTick: Float
            ) {
                val contents = getRelevantBones(animatable, relatedObject, renderState, partialTick)
                if (contents.isNotEmpty()) {
                    renderState.addGeckolibData(BlockAndItemGeoLayer.CONTENTS, contents)
                }
            }

            override fun submitItemStackRender(
                poseStack: com.mojang.blaze3d.vertex.PoseStack,
                bone: GeoBone,
                stack: ItemStackRenderState,
                displayContext: ItemDisplayContext,
                renderState: R,
                renderTasks: SubmitNodeCollector,
                packedLight: Int
            ) {
                poseStack.mulPose(org.joml.Matrix4f().rotation(Axis.XP.rotationDegrees(-90f)))
                super.submitItemStackRender(
                    poseStack,
                    bone,
                    stack,
                    displayContext,
                    renderState,
                    renderTasks,
                    packedLight
                )
            }
        })
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

        if (!(animatable.moistness <= -20 && !animatable.isInWater)) {
            renderState.addGeckolibData(
                HAPIDataTickets.TILT,
                Mth.clamp(Mth.lerp(partialTick, animatable.xRotO, animatable.xRot), -45f, 45f)
            )
            renderState.addGeckolibData(HAPIDataTickets.ROLL, turnRoll(animatable, partialTick))
        }
    }

    override fun scaleModelForRender(renderPassInfo: RenderPassInfo<R>, widthScale: Float, heightScale: Float) {
        val size = renderPassInfo.getGeckolibData(HAPIDataTickets.SIZE) ?: 1f
        super.scaleModelForRender(renderPassInfo, widthScale * size, heightScale * size)
    }

    override fun adjustModelBonesForRender(renderPassInfo: RenderPassInfo<R>, snapshots: BoneSnapshots) {
        val tilt = renderPassInfo.getGeckolibData(HAPIDataTickets.TILT) ?: return
        val roll = renderPassInfo.getGeckolibData(HAPIDataTickets.ROLL) ?: return

        snapshots.get(PartNames.HEAD).ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * -Mth.DEG_TO_RAD)
        }
        snapshots.get(PartNames.BODY).ifPresent { bone: BoneSnapshot ->
            bone.setRotX(tilt * -Mth.DEG_TO_RAD)
            bone.setRotZ(roll * -Mth.DEG_TO_RAD)
        }
        snapshots.get("body_2").ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * Mth.DEG_TO_RAD)
        }
        snapshots.get(PartNames.TAIL).ifPresent { bone: BoneSnapshot ->
            bone.setRotY(bone.getRotY() + roll * Mth.DEG_TO_RAD)
        }
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 0f
    }

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    companion object {
        /** Smoothed turn roll for the current render pass, updating the entity's roll state. */
        fun turnRoll(animatable: BaseDolphinEntity, partialTick: Float): Float {
            val yawDiff = animatable.yRot - animatable.yRotO
            val targetRoll = Mth.clamp(yawDiff * 3f, -15f, 15f)

            val turnSpeed = kotlin.math.abs(yawDiff)
            val smoothing = Mth.clamp(0.05f + turnSpeed * 0.02f, 0.05f, 0.25f)
            animatable.currentRoll = Mth.lerp(smoothing, animatable.currentRoll, targetRoll)

            return Mth.lerp(partialTick, animatable.prevRoll, animatable.currentRoll)
        }
    }
}
