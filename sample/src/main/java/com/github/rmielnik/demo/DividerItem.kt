package com.github.rmielnik.demo

import com.github.rmielnik.list.RecyclerItem

data class DividerItem(
    override val id: Int
) : RecyclerItem {

    override val viewHolderType = R.layout.item_divider
}