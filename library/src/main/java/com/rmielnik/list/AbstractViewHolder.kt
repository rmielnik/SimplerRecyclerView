package com.rmielnik.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Abstraction over [RecyclerView.ViewHolder] that exposes [bind] operation.
 */
abstract class AbstractViewHolder<in T : RecyclerItem>(
    view: View
) : RecyclerView.ViewHolder(view) {

    /**
     * Binds item data with view that is attached to this ViewHolder.
     */
    abstract fun bind(item: T)
}