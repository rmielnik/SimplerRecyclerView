package com.github.rmielnik.demo.sampleCustomAdapter

import android.view.View
import com.github.rmielnik.demo.databinding.ItemHeaderBinding
import com.github.rmielnik.list.AbstractViewHolder

class HeaderViewHolder(view: View) : AbstractViewHolder<HeaderItem>(view) {

    private val binding = ItemHeaderBinding.bind(view)

    override fun bind(item: HeaderItem) {
        binding.root.text = item.text
    }
}