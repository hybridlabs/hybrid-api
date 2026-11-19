package dev.hybridlabs.hapi.entity.base

/**
 * HAPI replacement for the vanilla `VariantHolder` interface, which was removed in 1.21.11.
 * Entity subclasses that expose a variant should implement this.
 */
interface VariantHolder<T> {
    var variant: T
}
