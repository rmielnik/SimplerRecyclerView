[![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](https://opensource.org/licenses/Apache-2.0)

# SimplerRecyclerView

SimplerRecyclerView is a tiny abstraction (<150 lines of code) wraps up all the boring and
tedious code that is required to make RecyclerView up and going and allows you to focus on important
stuff - creating items that represent data to be presented and ViewHolders that are able to update UI
accordingly.

## Usage

### Basic usage sample
Let's say you have a class `ParagraphItem `that represents data that will be displayed in `RecyclerView`
and a layout `R.layout.item_paragraph` that should be used by ViewHolder.

    data class ParagraphItem(
        val text: CharSequence
    )

First you need to implement `RecyclerItem` interface:

    data class ParagraphItem(
        override val id: Int,
        val text: CharSequence
    ) : RecyclerItem {

        override val viewHolderType = R.layout.item_paragraph
    }

Next you need to create a ViewHolder class that extends AbstractViewHolder, for example:

    class ParagraphViewHolder(view: View) : AbstractViewHolder<ParagraphItem>(view) {
        override fun bind(item: ParagraphItem) {
            (itemView as TextView).text = item.text
        }
    }

Now let's create an adapter that will be able to utilize both classes. The quickest approach is
to create a `ViewHolderRegistry` that is responsible for creating `AbstractViewHolder` for specific
`ParagraphItem::viewHolderType`:
 
    val factory = ViewHolderFactoryRegistry().apply {
        register(R.layout.item_paragraph) { ParagraphViewHolder(it) }
    }

Now you can create an adapter. Provided `SimpleListAdapter` is a very simple implementation that is
good enough for the most popular use cases. Simply create it and attach to view holder:

    adapter = SimpleListAdapter(factory)
    recyclerView.adapter = adapter

Please check `sampleViewHolderFactory` and `sampleCallbacks` packages for reference.

### Creating custom adapter

If you need to create custom adapter you may extend SimpleListAdapter, for example:
  
    class ExampleAdapter() : SimpleListAdapter<RecyclerItem>(
        viewHolderFactory = object : ViewHolderFactory<RecyclerItem> {
            override fun create(view: View, viewType: Int): AbstractViewHolder<RecyclerItem> {
                return when (viewType) {
                    R.layout.item_pargraph -> ParagraphViewHolder(view)
                    else -> error("Unsupported view type")
                } as AbstractViewHolder<RecyclerItem>
            }
        }
    )

### Registering factory functions in ViewHolderFactoryRegistry

There are three register methods that you can use when registering factory methods.

    val factory = ViewHolderFactoryRegistry().apply {
        // Option 1: make use of generics to validate types - if AViewHolder does not extend
        // AbstractViewHolder<ItemA> compilation will fail
        ItemA::class.register(R.layout.item_a) { AViewHolder(it) }

        // Option 2: make use of generics to validate types - provide class as an argument
        // IF BViewHolder does not extend AbstractViewHolder<ItemB> this method won't compile
        registerSafe(ItemB::class, R.layout.item_b) { BViewHolder(it) }

        // Option 3: don't validate types but make use of the most concise code
        register(R.layout.item_c) { CViewHolder(it) }
    }

### Using payloads to update view holder

In case you want to have more granular control over ViewHolder updates you may use 
`ListAdapter::onBindViewHolder(VH holder, int position, List<Object> payloads)` method.
`SimpleListAdapter` supports it out of the box. To use it you need to:
 1. In your `RecyclerItem` implement interface `UpdatableItem`.
 2. In your view holder change parent from `AbstractViewHolder` to `AbstractUpdatableViewHolder`

Please check `sampleCounter` package for reference.

License
-------

    Copyright 2020 Robert Mielnik

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.