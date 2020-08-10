package com.rmielnik.demo

import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.demo.items.HeaderItem
import com.rmielnik.demo.items.ParagraphItem
import com.rmielnik.demo.viewholders.ClockViewHolder
import com.rmielnik.demo.viewholders.HeaderViewHolder
import com.rmielnik.demo.viewholders.ParagraphViewHolder
import com.rmielnik.list.RecyclerItem
import com.rmielnik.list.SimpleListAdapter
import com.rmielnik.list.ViewHolderFactoryRegistry
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val dataFactory = ExampleDataFactory()
    private lateinit var counterJob: Job
    private lateinit var adapter: SimpleListAdapter<RecyclerItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recycler = findViewById<RecyclerView>(R.id.recycler)

        val onResetClickListener = this::onResetClick

        // Step 1 - Create factory that will be able to provide ViewHolders for given types of items
        val factory = ViewHolderFactoryRegistry().apply {
            // Example 1: make use of generics to validate types
            HeaderItem::class.register(R.layout.item_header) { HeaderViewHolder(it) }
            // Example 2: make use of generics to validate types - provide class as an argument
            registerSafe(ParagraphItem::class, R.layout.item_paragraph) { ParagraphViewHolder(it) }
            // Example 3: don't validate types but make use of the most concise code
            register(R.layout.item_clock) { ClockViewHolder(it, onResetClickListener) }
        }

        // Step 2 - the final one, create adapter (default DiffCallback is in most cases good enough)
        // and attach it to the RecyclerView
        adapter = SimpleListAdapter(factory).apply {
            submitList(dataFactory.createData())
            recycler.adapter = this
        }

        /*
        Alternative approach is to create concrete class like ExampleAdapter and attach it to
        RecyclerView
        adapter = ExampleAdapter(onResetClickListener).apply {
            submitList(dataFactory.createData())
            recycler.adapter = this
        }
         */

        counterJob = startCounter()
    }

    private fun startCounter() =
        lifecycleScope.launchWhenStarted {
            val startTime = SystemClock.elapsedRealtime()
            while (true) {
                val displayTime = SystemClock.elapsedRealtime() - startTime
                val data = dataFactory.createData(displayTime)
                adapter.submitList(data)
                delay(1_000)
            }
        }

    private fun onResetClick() {
        Toast.makeText(this, R.string.toast_reset_time, Toast.LENGTH_SHORT).show()
        counterJob.cancel()
        counterJob = startCounter()
    }
}