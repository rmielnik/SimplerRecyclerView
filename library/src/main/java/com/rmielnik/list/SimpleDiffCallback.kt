package com.rmielnik.list

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * Base implementation of DiffCallback. This implementation assumes that items are the same if
 * they have equal [RecyclerItem.id] and [RecyclerItem.viewHolderType]. Contents are the same
 * if content of the two items are equal.
 *
 * In addition if and item implements [UpdatableItem] then its [UpdatableItem.getUpdatePayload]
 * will be called to generate update payload.
 */
class SimpleDiffCallback<T : RecyclerItem> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.id == newItem.id && oldItem.viewHolderType == newItem.viewHolderType

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        if (oldItem is UpdatableItem) {
            return oldItem.getUpdatePayload(newItem)
        }
        return super.getChangePayload(oldItem, newItem)
    }
}