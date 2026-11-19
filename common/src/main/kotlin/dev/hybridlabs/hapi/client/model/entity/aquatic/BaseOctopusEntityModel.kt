package dev.hybridlabs.hapi.client.model.entity.aquatic

import dev.hybridlabs.hapi.entity.base.aquatic.BaseOctopusEntity
import net.minecraft.resources.Identifier
import software.bernie.geckolib.model.GeoModel
import software.bernie.geckolib.renderer.base.GeoRenderState

abstract class BaseOctopusEntityModel<T : BaseOctopusEntity>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "geo/cephalopod/$id/$id.geo.json")
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/cephalopod/$id/$id.png")
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "animations/entity/cephalopod/$id/$id.animation.json")
    }

    fun getLayerTextureResource(): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/cephalopod/$id/${id}_tint.png")
    }
}
