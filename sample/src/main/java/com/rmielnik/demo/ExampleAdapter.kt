package com.rmielnik.demo

import android.view.View
import com.rmielnik.demo.viewholders.ClockViewHolder
import com.rmielnik.demo.viewholders.HeaderViewHolder
import com.rmielnik.demo.viewholders.ParagraphViewHolder
import com.rmielnik.list.AbstractViewHolder
import com.rmielnik.list.RecyclerItem
import com.rmielnik.list.SimpleListAdapter
import com.rmielnik.list.ViewHolderFactory

class ExampleAdapter(
    onResetClick: () -> Unit
) : SimpleListAdapter<RecyclerItem>(
    viewHolderFactory = object : ViewHolderFactory<RecyclerItem> {
        override fun create(view: View, viewType: Int): AbstractViewHolder<RecyclerItem> {
            return when (viewType) {
                R.layout.item_clock -> ClockViewHolder(view, onResetClick)
                R.layout.item_header -> HeaderViewHolder(view)
                R.layout.item_paragraph -> ParagraphViewHolder(view)
                else -> error("Unsupported view type")
            } as AbstractViewHolder<RecyclerItem>
        }
    }
)