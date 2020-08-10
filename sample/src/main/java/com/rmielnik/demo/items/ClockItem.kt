package com.rmielnik.demo.items

import com.rmielnik.demo.R
import com.rmielnik.list.RecyclerItem
import com.rmielnik.list.UpdatableItem

data class ClockItem(
    override val id: Long,
    val elapsedTimeMs: Long
) : RecyclerItem, UpdatableItem {

    override val viewHolderType = R.layout.item_clock

    override fun getUpdatePayload(newItem: Any) =
        if (newItem is ClockItem) {
            newItem.elapsedTimeMs
        } else {
            null
        }
}