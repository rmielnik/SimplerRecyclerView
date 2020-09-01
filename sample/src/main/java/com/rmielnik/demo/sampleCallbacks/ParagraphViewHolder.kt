package com.rmielnik.demo.sampleCallbacks

import android.view.View
import android.widget.TextView
import com.rmielnik.list.AbstractViewHolder

class ParagraphViewHolder(view: View) : AbstractViewHolder<ParagraphItem>(view) {

    override fun bind(item: ParagraphItem) {
        (itemView as TextView).text = item.text
    }
}