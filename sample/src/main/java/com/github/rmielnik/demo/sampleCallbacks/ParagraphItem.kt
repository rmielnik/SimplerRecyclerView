package com.github.rmielnik.demo.sampleCallbacks

import com.github.rmielnik.demo.R
import com.github.rmielnik.list.RecyclerItem

data class ParagraphItem(
    override val id: Int,
    val text: CharSequence
) : RecyclerItem {

    override val viewHolderType = R.layout.item_paragraph
}