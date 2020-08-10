package com.rmielnik.demo.viewholders

import android.view.View
import com.rmielnik.demo.databinding.ItemHeaderBinding
import com.rmielnik.demo.items.HeaderItem
import com.rmielnik.list.AbstractViewHolder

class HeaderViewHolder(view: View) : AbstractViewHolder<HeaderItem>(view) {

    private val binding = ItemHeaderBinding.bind(view)

    override fun bind(item: HeaderItem) {
        binding.root.text = item.text
    }
}