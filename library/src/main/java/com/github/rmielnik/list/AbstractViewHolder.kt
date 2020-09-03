package com.github.rmielnik.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * An extension of [RecyclerView.ViewHolder] that exposes an abstract method for updating data in View.
 *
 * [AbstractViewHolder] exposes [bind] method that is used by [SimpleListAdapter.onBindViewHolder] to
 * automate updating View attached to this ViewHolder.
 *
 * @param T is a class that implements [RecyclerItem] or an interface that extends [RecyclerItem].
 * Instances of [T] can be passed to [bind] in order to be represented on a View.
 */
abstract class AbstractViewHolder<in T : RecyclerItem>(
    view: View
) : RecyclerView.ViewHolder(view) {

    /**
     * Binds item data with view that is attached to this [AbstractViewHolder].
     */
    abstract fun bind(item: T)
}