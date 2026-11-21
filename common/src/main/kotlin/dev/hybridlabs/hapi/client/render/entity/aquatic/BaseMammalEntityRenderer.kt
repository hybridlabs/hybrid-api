package dev.hybridlabs.hapi.client.render.entity.aquatic

import dev.hybridlabs.hapi.client.model.HABoneAdjustingModel

import com.mojang.math.Axis
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseMammalEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.client.renderer.item.ItemStackRenderState
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import com.geckolib.cache.model.GeoBone
import com.geckolib.constant.DataTickets
import com.geckolib.model.GeoModel
import com.geckolib.renderer.GeoEntityRenderer
import com.geckolib.renderer.base.BoneSnapshots
import com.geckolib.renderer.base.GeoRenderState
import com.geckolib.renderer.base.RenderPassInfo
import com.geckolib.renderer.layer.builtin.BlockAndItemGeoLayer

open class BaseMammalEntityRenderer<T : BaseMammalEntity, R : LivingEntityRenderState>(
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
                rotateItem(
                    poseStack,
                    bone,
                    renderState.getGeckolibData(HAPIDataTickets.MAIN_HAND_ITEM) ?: ItemStack.EMPTY,
                    renderState
                )

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

    protected open fun rotateItem(
        poseStack: com.mojang.blaze3d.vertex.PoseStack,
        bone: GeoBone,
        stack: ItemStack,
        renderState: R
    ) {
        poseStack.mulPose(org.joml.Matrix4f().rotation(Axis.XP.rotationDegrees(-90f)))
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
        (getGeoModel() as? HABoneAdjustingModel)?.adjustModelBones(renderPassInfo.renderState(), snapshots)
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
