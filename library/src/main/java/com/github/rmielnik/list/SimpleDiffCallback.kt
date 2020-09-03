package com.github.rmielnik.list

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * Default implementation of [androidx.recyclerview.widget.DiffUtil.ItemCallback].
 *
 * This class assumes that for each item in a data set a pair made of [RecyclerItem.id] and
 * [RecyclerItem.viewHolderType] is unique in the data set.
 *
 * [areItemsTheSame] returns true if oldItem and newItem have equal [RecyclerItem.id] and [RecyclerItem.viewHolderType].
 *
 * [areContentsTheSame] returns true if oldItem.equals(newItem)
 *
 * [getChangePayload] provides updatePayload if oldItem is an instance of [UpdatableItem].
 *
 * @param T is a class that implements [RecyclerItem] or an interface that extends [RecyclerItem].
 */
class SimpleDiffCallback<T : RecyclerItem> : DiffUtil.ItemCallback<T>() {

    /**
     * @return true if [oldItem] and [newItem] have equal [RecyclerItem.id] and [RecyclerItem.viewHolderType]
     */
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        oldItem.id == newItem.id && oldItem.viewHolderType == newItem.viewHolderType

    /**
     * @return true if [oldItem].equals([newItem])
     */
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    /**
     * @return if [oldItem] is an instance of [UpdatableItem] then [oldItem].getUpdatePayload([newItem])
     * will be called to generate update paylod
     */
    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        if (oldItem is UpdatableItem) {
            return oldItem.getUpdatePayload(newItem)
        }
        return super.getChangePayload(oldItem, newItem)
    }
}