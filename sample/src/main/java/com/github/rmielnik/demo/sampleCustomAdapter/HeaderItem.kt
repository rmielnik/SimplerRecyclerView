package com.github.rmielnik.demo.sampleCustomAdapter

import com.github.rmielnik.demo.R
import com.github.rmielnik.list.RecyclerItem

data class HeaderItem(
    override val id: Long,
    val text: CharSequence
) : RecyclerItem {

    override val viewHolderType = R.layout.item_header
}
