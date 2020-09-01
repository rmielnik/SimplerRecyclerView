package com.rmielnik.demo

import com.rmielnik.list.ViewHolderFactoryRegistry

val DIVIDER_FACTORY = ViewHolderFactoryRegistry().apply {
    register(R.layout.item_divider) { DividerViewHolder(it) }
}