package dev.hybridlabs.hapi.client.model

import software.bernie.geckolib.renderer.base.BoneSnapshots
import software.bernie.geckolib.renderer.base.GeoRenderState

/**
 * Implemented by models that adjust bones on top of the baked animation pose. GeckoLib 5 removed
 * model-level `setCustomAnimations`, so this is invoked from the base renderers instead.
 */
interface HABoneAdjustingModel {
    fun adjustModelBones(renderState: GeoRenderState, snapshots: BoneSnapshots) {}
}
