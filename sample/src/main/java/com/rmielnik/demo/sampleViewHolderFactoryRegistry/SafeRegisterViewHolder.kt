package com.rmielnik.demo.sampleViewHolderFactoryRegistry

import android.view.View
import android.widget.TextView
import com.rmielnik.list.AbstractViewHolder

class SafeRegisterViewHolder(view: View) : AbstractViewHolder<SafeRegisterItem>(view) {

    override fun bind(item: SafeRegisterItem) {
        (itemView as TextView).text = item.text
    }
}