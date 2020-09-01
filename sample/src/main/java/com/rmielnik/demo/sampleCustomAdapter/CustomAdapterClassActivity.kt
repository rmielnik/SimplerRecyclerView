package com.rmielnik.demo.sampleCustomAdapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.demo.R

class CustomAdapterClassActivity : AppCompatActivity() {

    private val loremIpsumHeader = HeaderItem(id = 1, text = "\"Lorem Ipsum\"")
    private val loremIpsumParagraphs = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Donec sed velit maximus, varius magna sed, hendrerit est.",
        "In tempor nisl at finibus congue.",
        "Etiam commodo tellus urna, at commodo nulla luctus in.",
        "Vestibulum tempor eleifend lorem, at condimentum ex finibus id. "
    ).mapIndexed { index, text -> ParagraphItem(index, text) }

    private val items = listOf(loremIpsumHeader) + loremIpsumParagraphs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_recycler)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val adapter = CustomAdapter()
        adapter.submitList(items)
        recycler.adapter = adapter
    }
}