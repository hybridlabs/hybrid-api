package dev.hybridlabs.hapi.client.render.entity.skies

import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import dev.hybridlabs.hapi.entity.base.skies.BaseRatiteEntity
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState
import net.minecraft.util.Mth
import com.geckolib.animation.state.BoneSnapshot
import com.geckolib.constant.DataTickets
import com.geckolib.model.GeoModel
import com.geckolib.renderer.GeoEntityRenderer
import com.geckolib.renderer.base.BoneSnapshots
import com.geckolib.renderer.base.RenderPassInfo

open class BaseRatiteEntityRenderer<T : BaseRatiteEntity, R : LivingEntityRenderState>(
    context: EntityRendererProvider.Context,
    model: GeoModel<T>
) : GeoEntityRenderer<T, R>(context, model) {

    init {
        this.shadowRadius = 0.3f
    }

    override fun addRenderData(animatable: T, relatedObject: Void?, renderState: R, partialTick: Float) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        HAPIDataTickets.captureEntityState(animatable, renderState)

        renderState.addGeckolibData(HAPIDataTickets.IS_BABY, animatable.isBaby)
    }

    override fun adjustModelBonesForRender(renderPassInfo: RenderPassInfo<R>, snapshots: BoneSnapshots) {
        val headPitch = renderPassInfo.getOrDefaultGeckolibData(DataTickets.ENTITY_PITCH, 0f) ?: 0f
        val netHeadYaw = renderPassInfo.getOrDefaultGeckolibData(DataTickets.ENTITY_YAW, 0f) ?: 0f

        snapshots.get("head").ifPresent { bone: BoneSnapshot ->
            bone.setRotX(headPitch * Mth.DEG_TO_RAD)
            bone.setRotY(netHeadYaw * Mth.DEG_TO_RAD)
        }
        snapshots.get("neck").ifPresent { bone: BoneSnapshot ->
            bone.setRotX(headPitch * Mth.DEG_TO_RAD / 2f)
            bone.setRotY(netHeadYaw * Mth.DEG_TO_RAD / 2f)
        }
    }
}
