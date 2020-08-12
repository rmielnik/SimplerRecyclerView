package com.rmielnik.demo

import com.rmielnik.demo.items.ClockItem
import com.rmielnik.demo.items.HeaderItem
import com.rmielnik.demo.items.ParagraphItem
import com.rmielnik.list.RecyclerItem

class ExampleDataFactory() {

    private val loremIpsumHeader = HeaderItem(id = 1, text = "\"Lorem Ipsum\"")

    private val loremIpsumParagraphs = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Donec sed velit maximus, varius magna sed, hendrerit est.",
        "In tempor nisl at finibus congue.",
        "Etiam commodo tellus urna, at commodo nulla luctus in.",
        "Vestibulum tempor eleifend lorem, at condimentum ex finibus id. "
    ).mapIndexed { index, text -> ParagraphItem(index, text) }


    fun createData(elapsedTimeMs: Long = 0L): List<RecyclerItem> =
        listOf(createClockItemForTick(elapsedTimeMs), loremIpsumHeader) + loremIpsumParagraphs

    private fun createClockItemForTick(elapsedTimeMs: Long) =
        ClockItem(elapsedTimeMs = elapsedTimeMs)
}