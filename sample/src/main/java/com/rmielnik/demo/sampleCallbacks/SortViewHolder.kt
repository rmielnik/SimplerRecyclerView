package com.rmielnik.demo.sampleCallbacks

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.demo.R
import com.rmielnik.list.AbstractViewHolder

class SortViewHolder(
    view: View,
    onSortClick: () -> Unit
) : AbstractViewHolder<SortItem>(view) {

    init {
        itemView.findViewById<Button>(R.id.cta_sort).setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onSortClick()
            }
        }
    }

    override fun bind(item: SortItem) {
    }
}