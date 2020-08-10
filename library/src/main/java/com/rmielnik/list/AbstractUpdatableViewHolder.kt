package com.rmielnik.list

import android.view.View

/**
 * Represents ViewHolder that is able to [bind] an item and an updatePayload created
 * by [androidx.recyclerview.widget.DiffUtil.ItemCallback.getChangePayload].
 */
abstract class AbstractUpdatableViewHolder<in T : RecyclerItem>(
    view: View
) : AbstractViewHolder<T>(view) {

    /**
     * Binds item data and updatePayload with view that is attached to this ViewHolder.
     */
    abstract fun bind(item: T, updatePayload: List<Any>?)
}