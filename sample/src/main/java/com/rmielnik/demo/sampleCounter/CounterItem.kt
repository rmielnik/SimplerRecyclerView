package com.rmielnik.demo.sampleCounter

import com.rmielnik.demo.R
import com.rmielnik.list.RecyclerItem
import com.rmielnik.list.UpdatableItem

data class CounterItem(
    val elapsedTimeMs: Long
) : RecyclerItem, UpdatableItem {

    override val id = CounterItem::class

    override val viewHolderType = R.layout.item_clock

    override fun getUpdatePayload(newItem: Any) =
        if (newItem is CounterItem) {
            newItem.elapsedTimeMs
        } else {
            null
        }
}