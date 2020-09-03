package com.github.rmielnik.list

import android.view.View
import androidx.annotation.LayoutRes
import kotlin.reflect.KClass

/**
 * Util class for creating customizable and easy to use ViewHolderFactory.
 *
 * In order to configure [ViewHolderFactoryRegistry] you need to provide rules how to create
 * [AbstractViewHolder] for given [RecyclerItem]. You do this by calling one of [register] or
 * [registerSafe] methods.
 *
 * There are three ways to provide a rule to [ViewHolderFactoryRegistry]. Assuming that:
 * - registry is some [ViewHolderFactoryRegistry] instance
 * - ItemA is a class implementing [RecyclerItem] that as a [RecyclerItem.viewHolderType]=R.layout.item_a
 * - AViewHolder is a class extending [AbstractViewHolder]<ItemA>
 *
 * You  can use one of methods:
 * - Without any validation:
 * ```
 * registry.register(R.layout.item_a) { view: View -> AViewHolder(view) }
 * ```
 *
 * - With validation that produced [AbstractViewHolder] can handle given [RecyclerItem]:
 * ```
 * registry.registerSafe(ItemA::class, R.layout.item_a) { view: View -> AViewHolder(view) }
 * ```
 *
 * - With validation that produced [AbstractViewHolder] can handle given [RecyclerItem] and an
 * extension function defined inside [ViewHolderFactoryRegistry]:
 * ```
 * registry.apply {
 *     ItemA::class.register(R.layout.item_a) { view: View -> AViewHolder(view) }
 * }
 * ```
 *
 * These three calls are equivalent.
 */
@Suppress("unused")
class ViewHolderFactoryRegistry : ViewHolderFactory<RecyclerItem> {

    internal val registry: MutableMap<Int, (View) -> AbstractViewHolder<*>> = HashMap()

    @Suppress("UNCHECKED_CAST")
    override fun create(view: View, @LayoutRes viewType: Int): AbstractViewHolder<RecyclerItem> =
        registry[viewType]?.invoke(view) as? AbstractViewHolder<RecyclerItem>
            ?: error("Unsupported view holder type")

    fun register(
        @LayoutRes viewType: Int,
        factoryMethod: (View) -> AbstractViewHolder<*>
    ) {
        registry[viewType] = factoryMethod
    }

    fun <C : RecyclerItem> KClass<C>.register(
        @LayoutRes viewType: Int,
        factoryMethod: (View) -> AbstractViewHolder<C>
    ) {
        registry[viewType] = factoryMethod
    }

    fun <C : RecyclerItem> registerSafe(
        @Suppress("UNUSED_PARAMETER") guard: KClass<C>,
        @LayoutRes viewType: Int,
        factoryMethod: (View) -> AbstractViewHolder<C>
    ) {
        registry[viewType] = factoryMethod
    }
}

/**
 * Creates new ViewHolderFactoryRegistry with all rules in both operands.
 */
operator fun ViewHolderFactoryRegistry.plus(other: ViewHolderFactoryRegistry) =
    ViewHolderFactoryRegistry().also {
        it.registry += registry
        it.registry += other.registry
    }