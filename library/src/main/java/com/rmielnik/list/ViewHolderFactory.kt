package com.rmielnik.list

import android.view.View
import androidx.annotation.LayoutRes

/**
 * Represents object that is able to create [AbstractViewHolder] or [AbstractUpdatableViewHolder]
 * for given [RecyclerItem.viewHolderType].
 *
 * @param T is a class that implements [RecyclerItem] or an interface that extends [RecyclerItem].
 */
interface ViewHolderFactory<in T : RecyclerItem> {

    /**
     * Creates [AbstractViewHolder] for given [view] and [viewType].
     *
     * @param view View that was created by LayoutInflater using [viewType]
     * @param viewType Layout resource id that is equal to some [RecyclerItem.viewHolderType] from the
     * data set
     */
    fun create(view: View, @LayoutRes viewType: Int): AbstractViewHolder<T>
}
