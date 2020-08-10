package com.rmielnik.demo.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.demo.databinding.ItemClockBinding
import com.rmielnik.demo.items.ClockItem
import com.rmielnik.list.AbstractUpdatableViewHolder
import java.util.concurrent.TimeUnit

class ClockViewHolder(view: View, onResetClick: () -> Unit) :
    AbstractUpdatableViewHolder<ClockItem>(view) {

    private val binding = ItemClockBinding.bind(view)
        .apply {
            ctaReset.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onResetClick()
                }
            }
        }

    override fun bind(item: ClockItem, updatePayloads: List<Any>?) {
        val latestUpdateTime = updatePayloads?.lastOrNull() ?: return bind(item)
        if (latestUpdateTime is Long) {
            updateElapsedTime(latestUpdateTime)
        }
    }

    override fun bind(item: ClockItem) {
        updateElapsedTime(item.elapsedTimeMs)
    }

    private fun updateElapsedTime(elapsedTimeMs: Long) {
        val hours =
            (TimeUnit.HOURS.convert(elapsedTimeMs, TimeUnit.MILLISECONDS) % 24).toPaddedString()
        val minutes =
            (TimeUnit.MINUTES.convert(elapsedTimeMs, TimeUnit.MILLISECONDS) % 60).toPaddedString()
        val seconds =
            (TimeUnit.SECONDS.convert(elapsedTimeMs, TimeUnit.MILLISECONDS) % 60).toPaddedString()
        binding.time.text = "$hours:$minutes:$seconds"
    }

    private fun Long.toPaddedString(length: Int = 2, padChar: Char = '0') =
        toString().padStart(length, padChar)
}