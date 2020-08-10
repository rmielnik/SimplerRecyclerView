package com.rmielnik.list

import android.view.View
import androidx.annotation.LayoutRes

/**
 * Factory that is responsible for creating proper [AbstractViewHolder] instances.
 */
interface ViewHolderFactory<in T : RecyclerItem> {

    /**
     * Creates [AbstractViewHolder] for given view and viewType.
     */
    fun create(view: View, @LayoutRes viewType: Int): AbstractViewHolder<T>
}
