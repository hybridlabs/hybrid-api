package dev.hybridlabs.hapi.client.render.entity.aquatic

import com.mojang.datafixers.util.Either
import com.mojang.math.Axis
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseMammalEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.state.CameraRenderState
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.cache.model.BakedGeoModel
import software.bernie.geckolib.cache.model.GeoBone
import software.bernie.geckolib.constant.DataTickets
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.BoneSnapshots
import software.bernie.geckolib.renderer.base.GeoRenderState
import software.bernie.geckolib.renderer.base.RenderPassInfo
import software.bernie.geckolib.renderer.layer.builtin.BlockAndItemGeoLayer

open class BaseMammalEntityRenderer<T : BaseMammalEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false
) : GeoEntityRenderer<T, R>(context, model) {

    private val itemBone: String = "item"

    init {
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

    override fun addRenderData(animatable: T, relatedObject: Void?, renderState: R, partialTick: Float) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        HAPIDataTickets.captureEntityState(animatable, renderState)

        renderState.addGeckolibData(HAPIDataTickets.IS_BABY, animatable.isBaby)
        renderState.addGeckolibData(HAPIDataTickets.MAIN_HAND_ITEM, animatable.mainHandItem)
        renderState.addGeckolibData(
            HAPIDataTickets.SIZE,
            if (variableSize) BaseWaterAnimal.getScaleAdjustment(animatable, 0.05f) else 1f
        )
    }

    override fun scaleModelForRender(renderPassInfo: RenderPassInfo<R>, widthScale: Float, heightScale: Float) {
        val size = renderPassInfo.getGeckolibData(HAPIDataTickets.SIZE) ?: 1f
        super.scaleModelForRender(renderPassInfo, widthScale * size, heightScale * size)
    }

    override fun adjustModelBonesForRender(renderPassInfo: RenderPassInfo<R>, snapshots: BoneSnapshots) {
        val headPitch = renderPassInfo.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f
        val netHeadYaw = renderPassInfo.getOrDefaultGeckolibData(DataTickets.ENTITY_YAW, 0f) ?: 0f

        snapshots.get("head").ifPresent { bone ->
            bone.setRotX(headPitch * Mth.DEG_TO_RAD)
            bone.setRotY(netHeadYaw * Mth.DEG_TO_RAD)
        }
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 0f
    }

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }
}
