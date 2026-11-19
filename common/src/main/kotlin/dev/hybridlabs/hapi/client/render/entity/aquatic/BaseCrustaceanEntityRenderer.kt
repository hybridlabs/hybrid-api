package dev.hybridlabs.hapi.client.render.entity.aquatic

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.aquatic.BaseCrustaceanEntity
import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.base.GeoRenderState
import software.bernie.geckolib.renderer.base.RenderPassInfo
import software.bernie.geckolib.renderer.layer.builtin.AutoGlowingGeoLayer

@Suppress("LeakingThis")
open class BaseCrustaceanEntityRenderer<T : BaseCrustaceanEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>,
    private var variableSize: Boolean = false,
    canGlow: Boolean = false
) : GeoEntityRenderer<T, R>(context, model) {

    override fun getMotionAnimThreshold(animatable: T): Float {
        return 0.0025f
    }

    init {
        this.shadowRadius = 0.3f
    }

    init {
        if (canGlow) withRenderLayer(AutoGlowingGeoLayer(this))
    }

    override fun getDeathMaxRotation(renderState: GeoRenderState): Float {
        return 0f
    }

    override fun addRenderData(animatable: T, relatedObject: Void?, renderState: R, partialTick: Float) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        HAPIDataTickets.captureEntityState(animatable, renderState)

        renderState.addGeckolibData(HAPIDataTickets.IS_BABY, animatable.isBaby)
        renderState.addGeckolibData(
            HAPIDataTickets.SIZE,
            if (variableSize) BaseWaterAnimal.getScaleAdjustment(animatable, 0.05f) else 1f
        )
    }

    override fun scaleModelForRender(renderPassInfo: RenderPassInfo<R>, widthScale: Float, heightScale: Float) {
        val size = renderPassInfo.getGeckolibData(HAPIDataTickets.SIZE) ?: 1f
        super.scaleModelForRender(renderPassInfo, widthScale * size, heightScale * size)
    }
}
