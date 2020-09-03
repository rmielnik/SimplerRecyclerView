package com.github.rmielnik.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.github.rmielnik.demo.sampleCallbacks.CallbacksActivity
import com.github.rmielnik.demo.sampleCounter.CounterActivity
import com.github.rmielnik.demo.sampleCustomAdapter.CustomAdapterClassActivity
import com.github.rmielnik.demo.sampleViewHolderFactoryRegistry.ViewHolderFactoryRegistryActivity
import com.github.rmielnik.list.SimpleListAdapter
import com.github.rmielnik.list.ViewHolderFactoryRegistry
import com.github.rmielnik.list.plus

class MainActivity : AppCompatActivity(R.layout.screen_recycler) {

    private val samples = listOf(
        SampleItem(
            title = "Custom adapter class",
            description = "Presents custom adapter",
            targetActivityClass = CustomAdapterClassActivity::class.java
        ),
        DividerItem(1),
        SampleItem(
            title = "ViewHolderFactoryRegistry sample",
            description = "Presents how to utilize ViewHolderFactoryRegistry with SimpleListAdapter",
            targetActivityClass = ViewHolderFactoryRegistryActivity::class.java
        ),
        DividerItem(2),
        SampleItem(
            title = "Pass callbacks to ViewHolders",
            description = "Presents how to pass click listeners to view holders",
            targetActivityClass = CallbacksActivity::class.java
        ),
        DividerItem(3),
        SampleItem(
            title = "ViewHolder updates with payloads",
            description = "Presents how to use payload to update view holders",
            targetActivityClass = CounterActivity::class.java
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val factory = ViewHolderFactoryRegistry().apply {
            register(R.layout.item_sample) { SampleViewHolder(it) }
            register(R.layout.item_sample) { SampleViewHolder(it) }
        } + DIVIDER_FACTORY

        val adapter = SimpleListAdapter(factory)
        adapter.submitList(samples)
        recycler.adapter = adapter
    }
}