package com.rmielnik.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rmielnik.list.AbstractUpdatableViewHolder

/**
 * Simple but highly configurable [androidx.recyclerview.widget.ListAdapter].
 *
 * @param viewHolderFactory - object that is able to create ViewHolders for given viewTypes
 * @param diffCallback - an implementation od DiffUtil.ItemCallback to be used when new data
 * will be submitted to be displayed.
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
        super.onBindViewHolder(holder, position, payloads)
    }
}