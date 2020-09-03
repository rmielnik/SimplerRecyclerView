package com.github.rmielnik.demo.sampleViewHolderFactoryRegistry

import android.view.View
import android.widget.TextView
import com.github.rmielnik.list.AbstractViewHolder

class SafeRegisterAlternativeViewHolder(
    view: View
) : AbstractViewHolder<SafeRegisterAlternativeItem>(view) {

    override fun bind(item: SafeRegisterAlternativeItem) {
        (itemView as TextView).text = item.text
    }
}