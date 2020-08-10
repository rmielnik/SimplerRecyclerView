package com.rmielnik.list

import androidx.annotation.LayoutRes

/**
 * Represents an item that will be displayed a RecyclerView.
 */
interface RecyclerItem {

    /**
     * Identifier of the item.
     */
    val id: Long

    /**
     * Value of a layout resource with view that is dedicated to represent this item's data.
     */
    @get:LayoutRes
    val viewHolderType: Int
}