This is a small abstraction (<150 lines of code) focused on removing mundane parts of RecyclerView - 
ViewHolders creation and binding them with items from the adapter. There are no complex classes nor 
fancy solutions, just simplification of existing mechanisms with tiny abstraction. 
 
Usage
-----

 1. Create classes representing data. Let them implement `RecyclerItem` interface. As a 
 viewHolderType provide layout resource that will be used by LayoutInflater to create View for a ViewHolder.
 2. Create dedicated `AbstractViewHolder` classes for each item you just created.
 3. Create an adapter using `SimpleListAdapter` and `ViewHolderFactory`, use one of the suggested
 approaches:
  *  **Verbose approach** - Create custom Adapter class:
         class ExampleAdapter() : SimpleListAdapter<RecyclerItem>(
             viewHolderFactory = object : ViewHolderFactory<RecyclerItem> {
                 override fun create(view: View, viewType: Int): AbstractViewHolder<RecyclerItem> {
                     return when (viewType) {
                         R.layout.item_a -> AViewHolder(view)
                         R.layout.item_b -> BViewHolder(view)
                         R.layout.item_c -> CViewHolder(view)
                         ...
                         else -> error("Unsupported view type")
                     } as AbstractViewHolder<RecyclerItem>
                 }
             }
         )
  *  **Lazy approach** - Create ViewHolderFactoryRegistry and provide it to SimpleListAdapter:
        val factory = ViewHolderFactoryRegistry().apply {
            // Option 1: make use of generics to validate types
            ItemA::class.register(R.layout.item_a) { AViewHolder(it) }
            // Option 2: make use of generics to validate types - provide class as an argument
            registerSafe(ItemB::class, R.layout.item_b) { BViewHolder(it) }
            // Option 3: don't validate types but make use of the most concise code
            register(R.layout.item_c) { CViewHolder(it) }
        }
        adapter = SimpleListAdapter(factory)
 4. Attach your adapter to a RecyclerView.

That's it. You can sample implementation MainActivity and ExampleAdapter in the sample.

Updating items on data change
-----

In case you want to have more granular control over ViewHolder updates you may use 
`ListAdapter::onBindViewHolder(VH holder, int position, List<Object> payloads)` method.
`SimpleListAdapter` supports it out of the box. To utilize you need to:
 1. In your `RecyclerItem` implement interface `UpdatableItem`.
 2. In your view holder change parent from `AbstractViewHolder` to `AbstractUpdatableViewHolder`
 
That's it. You can sample implementation in ClockItem and ClockViewHolder in the sample.

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