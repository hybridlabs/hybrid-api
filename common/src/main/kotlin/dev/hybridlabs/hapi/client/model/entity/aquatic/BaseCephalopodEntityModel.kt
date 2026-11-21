package dev.hybridlabs.hapi.client.model.entity.aquatic

import dev.hybridlabs.hapi.entity.base.aquatic.BaseCephalopodEntity
import net.minecraft.resources.Identifier
import com.geckolib.model.GeoModel
import com.geckolib.renderer.base.GeoRenderState

abstract class BaseCephalopodEntityModel<T : BaseCephalopodEntity>(
    private val namespace: String,
    private val id: String
) : GeoModel<T>() {

    override fun getModelResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "cephalopod/$id/$id"
        )
    }

    override fun getTextureResource(renderState: GeoRenderState): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "textures/entity/cephalopod/$id/$id.png"
        )
    }

    override fun getAnimationResource(animatable: T): Identifier {
        return Identifier.fromNamespaceAndPath(
            namespace,
            "entity/cephalopod/$id/$id"
        )
    }
}
