package com.rmielnik.demo.sampleViewHolderFactoryRegistry

import com.rmielnik.demo.R
import com.rmielnik.list.RecyclerItem

data class ParagraphItem(
    override val id: Int,
    val text: CharSequence
) : RecyclerItem {

    override val viewHolderType = R.layout.item_paragraph
}