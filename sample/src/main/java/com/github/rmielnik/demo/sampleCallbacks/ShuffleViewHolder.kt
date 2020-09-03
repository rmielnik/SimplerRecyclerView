package com.github.rmielnik.demo.sampleCallbacks

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.github.rmielnik.demo.R
import com.github.rmielnik.list.AbstractViewHolder

class ShuffleViewHolder(
    view: View,
    onShuffleClick: () -> Unit
) : AbstractViewHolder<ShuffleItem>(view) {

    init {
        itemView.findViewById<Button>(R.id.cta_shuffle).setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onShuffleClick()
            }
        }
    }

    override fun bind(item: ShuffleItem) {
    }
}