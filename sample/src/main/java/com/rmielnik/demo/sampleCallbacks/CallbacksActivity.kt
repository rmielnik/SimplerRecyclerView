package com.rmielnik.demo.sampleCallbacks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.demo.R
import com.rmielnik.list.RecyclerItem
import com.rmielnik.list.SimpleListAdapter
import com.rmielnik.list.ViewHolderFactoryRegistry

class CallbacksActivity : AppCompatActivity() {

    private val loremIpsumParagraphs = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Donec sed velit maximus, varius magna sed, hendrerit est.",
        "In tempor nisl at finibus congue.",
        "Etiam commodo tellus urna, at commodo nulla luctus in.",
        "Vestibulum tempor eleifend lorem, at condimentum ex finibus id. "
    ).mapIndexed { index, text -> ParagraphItem(index, text) }

    private val sortedItems = loremIpsumParagraphs + ShuffleItem + SortItem

    private lateinit var adapter: SimpleListAdapter<RecyclerItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_recycler)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val factory = ViewHolderFactoryRegistry().apply {

            register(R.layout.item_shuffle) {
                ShuffleViewHolder(it) {
                    shuffleParagraphs()
                }
            }

            register(R.layout.item_sort) {
                SortViewHolder(it, this@CallbacksActivity::sortParagraphs)
            }

            register(R.layout.item_paragraph) { ParagraphViewHolder(it) }
        }

        adapter = SimpleListAdapter(factory)
        adapter.submitList(sortedItems)
        recycler.adapter = adapter
    }

    private fun sortParagraphs() {
        adapter.submitList(sortedItems)
    }

    private fun shuffleParagraphs() {
        adapter.submitList(sortedItems.shuffled())
    }
}