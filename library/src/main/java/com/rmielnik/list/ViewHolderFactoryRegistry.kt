package com.rmielnik.list

import android.view.View
import androidx.annotation.LayoutRes
import kotlin.reflect.KClass

/**
 * Util class for creating ViewHolderFactory.
 */
@Suppress("unused")
class ViewHolderFactoryRegistry(
    private val registry: HashMap<Int, (View) -> AbstractViewHolder<*>> = HashMap()
) : ViewHolderFactory<RecyclerItem> {

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