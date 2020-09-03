package com.github.rmielnik.demo.sampleViewHolderFactoryRegistry

import com.github.rmielnik.demo.R
import com.github.rmielnik.list.RecyclerItem

data class SafeRegisterAlternativeItem(
    override val id: Long,
    val text: CharSequence
) : RecyclerItem {

    override val viewHolderType = R.layout.item_safe_register_alternative
}
