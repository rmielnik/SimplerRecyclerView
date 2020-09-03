package com.github.rmielnik.demo

import com.github.rmielnik.list.ViewHolderFactoryRegistry

val DIVIDER_FACTORY = ViewHolderFactoryRegistry().apply {
    register(R.layout.item_divider) { DividerViewHolder(it) }
}