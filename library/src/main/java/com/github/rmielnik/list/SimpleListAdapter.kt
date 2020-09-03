package com.github.rmielnik.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * An implementation of [androidx.recyclerview.widget.ListAdapter].
 *
 * This is a class that connects all the abstractions introduced in this library into one customizable
 * adapter. There is a great chance that you won't need to create any other Adapter class anymore.
 *
 * This is how [SimpleListAdapter] utilizes [RecyclerItem] and [AbstractViewHolder]:
 * - [onCreateViewHolder] creates Views by providing [RecyclerItem.viewHolderType] to a [LayoutInflater].
 * A newly created view is passed to [ViewHolderFactory.create] with corresponding [RecyclerItem.viewHolderType].
 * [ViewHolderFactory] creates [AbstractViewHolder] that is used by RecyclerView.
 * - [onBindViewHolder] for given position gets [RecyclerItem] from the data set and passes it to
 * [AbstractViewHolder.bind] or [AbstractUpdatableViewHolder.bind] method in order to update ViewHolder.
 *
 * @param viewHolderFactory is an object that is able to create [AbstractViewHolder] for given
 * [RecyclerItem.viewHolderType] from the data set.
 * @param diffCallback an implementation of [androidx.recyclerview.widget.DiffUtil.ItemCallback]
 * to be used when new data sets are submitted. By default [SimpleDiffCallback] is used.
 * @param T is a class that implements [RecyclerItem] or an interface that extends [RecyclerItem].
 */
open class SimpleListAdapter<T : RecyclerItem>(
    private val viewHolderFactory: ViewHolderFactory<T>,
    diffCallback: DiffUtil.ItemCallback<T> = SimpleDiffCallback()
) : ListAdapter<T, AbstractViewHolder<T>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        with(LayoutInflater.from(parent.context)) {
            val view = inflate(viewType, parent, false)
            viewHolderFactory.create(view, viewType)
        }

    override fun getItemViewType(position: Int) =
        getItem(position).viewHolderType

    override fun onBindViewHolder(viewHolder: AbstractViewHolder<T>, position: Int) =
        viewHolder.bind(getItem(position))

    override fun onBindViewHolder(
        holder: AbstractViewHolder<T>,
        position: Int,
        payloads: List<Any>
    ) = if (holder is AbstractUpdatableViewHolder) {
        holder.bind(getItem(position), payloads)
    } else {
        holder.bind(getItem(position))
    }
}