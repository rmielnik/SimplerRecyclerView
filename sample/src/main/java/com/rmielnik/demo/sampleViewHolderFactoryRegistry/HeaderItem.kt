package com.rmielnik.demo.sampleViewHolderFactoryRegistry

import com.rmielnik.demo.R
import com.rmielnik.list.RecyclerItem

data class HeaderItem(
    override val id: Long,
    val text: CharSequence
) : RecyclerItem {

    override val viewHolderType = R.layout.item_header
}
