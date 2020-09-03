package com.github.rmielnik.demo.sampleCounter

import com.github.rmielnik.demo.R
import com.github.rmielnik.list.RecyclerItem
import com.github.rmielnik.list.UpdatableItem

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