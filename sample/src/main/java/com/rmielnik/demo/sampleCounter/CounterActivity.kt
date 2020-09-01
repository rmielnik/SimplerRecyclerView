package com.rmielnik.demo.sampleCounter

import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.demo.R
import com.rmielnik.list.RecyclerItem
import com.rmielnik.list.SimpleListAdapter
import com.rmielnik.list.ViewHolderFactoryRegistry
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class CounterActivity : AppCompatActivity() {

    private lateinit var counterJob: Job
    private lateinit var adapter: SimpleListAdapter<RecyclerItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_recycler)
        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val factory = ViewHolderFactoryRegistry().apply {
            register(R.layout.item_clock) {
                CounterViewHolder(
                    view = it,
                    onResetClick = this@CounterActivity::onResetClick
                )
            }
        }

        adapter = SimpleListAdapter(factory)
        adapter.submitList(createCounterItem(0))
        recycler.adapter = adapter
        counterJob = startCounter()
    }

    private fun createCounterItem(elapsedTimeMs: Long) = listOf(
        CounterItem(elapsedTimeMs = elapsedTimeMs)
    )

    private fun startCounter() =
        lifecycleScope.launchWhenStarted {
            val startTime = SystemClock.elapsedRealtime()
            while (true) {
                val displayTime = SystemClock.elapsedRealtime() - startTime
                adapter.submitList(createCounterItem(displayTime))
                delay(1_000)
            }
        }

    private fun onResetClick() {
        Toast.makeText(this, R.string.toast_reset_time, Toast.LENGTH_SHORT).show()
        counterJob.cancel()
        counterJob = startCounter()
    }
}