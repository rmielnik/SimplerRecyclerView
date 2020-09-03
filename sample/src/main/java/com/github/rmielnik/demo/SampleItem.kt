package com.github.rmielnik.demo

import com.github.rmielnik.list.RecyclerItem

data class SampleItem(
    val title: String,
    val description: String,
    val targetActivityClass: Class<*>
) : RecyclerItem {

    override val id = targetActivityClass
    override val viewHolderType = R.layout.item_sample
}