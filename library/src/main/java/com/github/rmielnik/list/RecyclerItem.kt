package com.github.rmielnik.list

import androidx.annotation.LayoutRes

/**
 * Represents an object that will be displayed in RecyclerView.
 *
 * [RecyclerItem] has two properties that are used to automate tasks like comparison of the objects
 * when updating data or ViewHolders creation.
 *
 * It is important to keep a pair of [id] and [viewHolderType] unique. Assumption of uniqueness
 * is used by [SimpleDiffCallback] to provide reasonable default [androidx.recyclerview.widget.DiffUtil.ItemCallback]
 * implementation.
 *
 * [id] is an identifier of the item. For example this could be an id of a business object that is
 * represented by this [RecyclerItem].
 *
 * [viewHolderType] is used to identify layout which is dedicated to display data stored
 * in [RecyclerItem].
 *
 * @see SimpleDiffCallback
 * @see SimpleListAdapter
 */
interface RecyclerItem {

    /**
     * Identifier of the item.
     */
    val id: Any

    /**
     * Identifier of a layout that should be used to display the data on a screen.
     */
    @get:LayoutRes
    val viewHolderType: Int
}