package dev.hybridlabs.hapi.client.model.entity.aquatic

import dev.hybridlabs.hapi.entity.base.aquatic.BaseCritterEntity
import net.minecraft.resources.Identifier
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

abstract class BaseCritterEntityModel<T : BaseCritterEntity>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "geo/critter/$id/$id.geo.json")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/critter/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "animations/entity/critter/$id/$id.animation.json")
    }

    open fun getLayerTextureResource(layer: String): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/critter/$id/layers/${id}_$layer.png")
    }
}
