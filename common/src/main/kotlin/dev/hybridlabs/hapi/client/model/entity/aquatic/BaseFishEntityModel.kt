package dev.hybridlabs.hapi.client.model.entity.aquatic

import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import net.minecraft.resources.Identifier
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

abstract class BaseFishEntityModel<T : BaseFishEntity>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "geo/fish/$id/$id.geo.json")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/fish/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "animations/entity/fish/$id/$id.animation.json")
    }

    fun getLayerTextureResource(layer: String): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/fish/$id/layers/${id}_$layer.png")
    }
}
