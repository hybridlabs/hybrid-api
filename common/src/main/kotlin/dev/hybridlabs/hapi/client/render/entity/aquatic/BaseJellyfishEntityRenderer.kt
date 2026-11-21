package dev.hybridlabs.hapi.client.render.entity.aquatic

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseJellyfishEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.util.Mth
import com.geckolib.model.GeoModel
import com.geckolib.renderer.GeoEntityRenderer
import com.geckolib.renderer.base.GeoRenderState
import com.geckolib.renderer.base.RenderPassInfo
import com.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class BaseJellyfishEntityRenderer<T : BaseJellyfishEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
    canGlow: Boolean = false
) : GeoEntityRenderer<T, R>(context, model) {

    init {
        if (canGlow) withRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun addRenderData(animatable: T, relatedObject: Void?, renderState: R, partialTick: Float) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        HAPIDataTickets.captureEntityState(animatable, renderState)

        renderState.addGeckolibData(HAPIDataTickets.IS_BABY, animatable.isBaby)
        renderState.addGeckolibData(
            HAPIDataTickets.SIZE,
            if (variableSize) BaseWaterAnimal.getScaleAdjustment(animatable, 0.05f) else 1f
        )
        renderState.addGeckolibData(
            HAPIDataTickets.TILT_ANGLE,
            Mth.lerp(partialTick, animatable.prevTiltAngle, animatable.tiltAngle)
        )
    }

    override fun applyRotations(renderPassInfo: RenderPassInfo<R>, poseStack: PoseStack, nativeScale: Float) {
        val renderState = renderPassInfo.renderState()
        val tiltAngle = renderState.getGeckolibData(HAPIDataTickets.TILT_ANGLE) ?: 0f
        val rotationYaw = renderState.getOrDefaultGeckolibData(
            com.geckolib.constant.DataTickets.ENTITY_BODY_YAW, 0f
        ) ?: 0f

        poseStack.translate(0.0f, 0.25f, 0.0f)
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f - rotationYaw))
        poseStack.mulPose(Axis.XP.rotationDegrees(tiltAngle))
        poseStack.translate(0.0f, 0.0f, 0.0f)
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 0f
    }

    override fun scaleModelForRender(renderPassInfo: RenderPassInfo<R>, widthScale: Float, heightScale: Float) {
        val size = renderPassInfo.getGeckolibData(HAPIDataTickets.SIZE) ?: 1f
        super.scaleModelForRender(renderPassInfo, widthScale * size, heightScale * size)
    }
}
