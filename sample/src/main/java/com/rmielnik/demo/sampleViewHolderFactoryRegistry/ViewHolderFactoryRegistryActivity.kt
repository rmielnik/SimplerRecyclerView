package com.rmielnik.demo.sampleViewHolderFactoryRegistry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.rmielnik.demo.DIVIDER_FACTORY
import com.rmielnik.demo.DividerItem
import com.rmielnik.demo.R
import com.rmielnik.list.SimpleListAdapter
import com.rmielnik.list.ViewHolderFactoryRegistry
import com.rmielnik.list.plus

class ViewHolderFactoryRegistryActivity : AppCompatActivity() {

    private val loremIpsumHeader = HeaderItem(id = 1, text = "\"Lorem Ipsum\"")
    private val loremIpsumParagraphs = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
        "Donec sed velit maximus, varius magna sed, hendrerit est.",
        "In tempor nisl at finibus congue.",
        "Etiam commodo tellus urna, at commodo nulla luctus in.",
        "Vestibulum tempor eleifend lorem, at condimentum ex finibus id. "
    ).mapIndexed { index, text -> ParagraphItem(index, text) }

    private val safelyRegisteredItems = listOf(
        DividerItem(1),
        SafeRegisterItem(1, "Type registered using KClass' extension"),
        DividerItem(2),
        SafeRegisterAlternativeItem(1, "Type registered using registerSafe function")
    )

    private val items = listOf(loremIpsumHeader) + loremIpsumParagraphs + safelyRegisteredItems

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_recycler)

        val recycler = findViewById<RecyclerView>(R.id.recycler)

        val factory = ViewHolderFactoryRegistry().apply {
            register(R.layout.item_header) { HeaderViewHolder(it) }
            register(R.layout.item_paragraph) { ParagraphViewHolder(it) }
        }

        val safeFactory =  ViewHolderFactoryRegistry().apply {
            // RegisterSafe function that checks view holder and item compatibility
            registerSafe(SafeRegisterAlternativeItem::class, R.layout.item_safe_register_alternative) {
                SafeRegisterAlternativeViewHolder(it)
            }

            // Register function that checks view holder and item compatibility - implemented
            // as extension on KClass<RecyclerItem>
            SafeRegisterItem::class.register(R.layout.item_safe_register) { SafeRegisterViewHolder(it) }
        }

        // Plus operator is defined for ViewHolderFactoryRegistries. You can extract definitions of
        // items to a factory. Then you can append these definitions to other factory using '+'.
        val mergedFactories = factory + safeFactory + DIVIDER_FACTORY

        val adapter = SimpleListAdapter(mergedFactories)
        adapter.submitList(items)
        recycler.adapter = adapter
    }
}