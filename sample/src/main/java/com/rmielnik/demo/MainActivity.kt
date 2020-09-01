package com.rmielnik.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.demo.sampleCallbacks.CallbacksActivity
import com.rmielnik.demo.sampleCounter.CounterActivity
import com.rmielnik.demo.sampleCustomAdapter.CustomAdapterClassActivity
import com.rmielnik.demo.sampleViewHolderFactoryRegistry.ViewHolderFactoryRegistryActivity
import com.rmielnik.list.SimpleListAdapter
import com.rmielnik.list.ViewHolderFactoryRegistry

class MainActivity : AppCompatActivity(R.layout.screen_recycler) {

    private val samples = listOf(
        SampleItem(
            title = "Custom adapter class",
            description = "Presents custom adapter",
            targetActivityClass = CustomAdapterClassActivity::class.java
        ),
        SampleItem(
            title = "ViewHolderFactoryRegistry sample",
            description = "Presents how to utilize ViewHolderFactoryRegistry with SimpleListAdapter",
            targetActivityClass = ViewHolderFactoryRegistryActivity::class.java
        ),
        SampleItem(
            title = "Pass callbacks to ViewHolders",
            description = "Presents how to pass click listeners to view holders",
            targetActivityClass = CallbacksActivity::class.java
        ),
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
        }

        val adapter = SimpleListAdapter(factory)
        adapter.submitList(samples)
        recycler.adapter = adapter
    }
}