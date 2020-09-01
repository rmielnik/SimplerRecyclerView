package com.rmielnik.demo.sampleViewHolderFactoryRegistry

import com.rmielnik.demo.R
import com.rmielnik.list.RecyclerItem

data class SafeRegisterItem(
    override val id: Long,
    val text: CharSequence
) : RecyclerItem {

    override val viewHolderType = R.layout.item_safe_register
}