package com.github.rmielnik.demo.sampleCustomAdapter

import android.view.View
import com.github.rmielnik.demo.R
import com.github.rmielnik.list.AbstractViewHolder
import com.github.rmielnik.list.RecyclerItem
import com.github.rmielnik.list.SimpleListAdapter
import com.github.rmielnik.list.ViewHolderFactory

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