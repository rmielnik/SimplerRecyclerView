package com.rmielnik.demo.sampleCustomAdapter

import android.view.View
import com.rmielnik.demo.R
import com.rmielnik.list.AbstractViewHolder
import com.rmielnik.list.RecyclerItem
import com.rmielnik.list.SimpleListAdapter
import com.rmielnik.list.ViewHolderFactory

class CustomAdapter : SimpleListAdapter<RecyclerItem>(
    viewHolderFactory = object : ViewHolderFactory<RecyclerItem> {
        override fun create(view: View, viewType: Int): AbstractViewHolder<RecyclerItem> {
            return when (viewType) {
                R.layout.item_header -> HeaderViewHolder(view)
                R.layout.item_paragraph -> ParagraphViewHolder(view)
                else -> error("Unsupported view type")
            } as AbstractViewHolder<RecyclerItem>
        }
    }
)