package com.rmielnik.demo

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.list.AbstractViewHolder

class SampleViewHolder(view: View) : AbstractViewHolder<SampleItem>(view) {

    private val title = view.findViewById<TextView>(R.id.title)
    private val description = view.findViewById<TextView>(R.id.description)

    override fun bind(item: SampleItem) {
        title.text = item.title
        description.text = item.description

        itemView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                with(itemView.context) {
                    startActivity(Intent(this, item.targetActivityClass))
                }
            }
        }
    }
}