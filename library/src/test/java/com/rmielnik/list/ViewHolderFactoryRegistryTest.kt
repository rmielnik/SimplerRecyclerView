package com.rmielnik.list

import android.view.View
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.Assert
import org.junit.Before
import org.junit.Test

const val TEST_LAYOUT_ID_A = 1
const val TEST_LAYOUT_ID_B = 2

class ViewHolderFactoryRegistryTest {

    @MockK
    private lateinit var view: View

    @MockK
    private lateinit var viewHolder: AbstractViewHolder<RecyclerItem>

    @MockK
    private lateinit var factoryMethodA: (View) -> AbstractViewHolder<RecyclerItem>

    @MockK
    private lateinit var factoryMethodB: (View) -> AbstractViewHolder<RecyclerItem>

    private lateinit var emptyRegistry: ViewHolderFactoryRegistry
    private lateinit var registryA: ViewHolderFactoryRegistry
    private lateinit var registryB: ViewHolderFactoryRegistry

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { factoryMethodA.invoke(view) } returns viewHolder
        every { factoryMethodB.invoke(view) } returns viewHolder

        emptyRegistry = ViewHolderFactoryRegistry()

        registryA = ViewHolderFactoryRegistry().apply {
            register(TEST_LAYOUT_ID_A, factoryMethodA)
        }

        registryB = ViewHolderFactoryRegistry().apply {
            register(TEST_LAYOUT_ID_B, factoryMethodB)
        }
    }

    @Test
    fun `should register factory without type checks`() {
        emptyRegistry.register(TEST_LAYOUT_ID_A, factoryMethodA)
        val result = emptyRegistry.create(view, TEST_LAYOUT_ID_A)

        verify(exactly = 1) { factoryMethodA.invoke(view) }
        Assert.assertEquals(viewHolder, result)
    }

    @Test
    fun `should register factory with type checks`() {
        emptyRegistry.apply {
            RecyclerItem::class.register(TEST_LAYOUT_ID_A, factoryMethodA)
        }
        val result = emptyRegistry.create(view, TEST_LAYOUT_ID_A)

        verify(exactly = 1) { factoryMethodA.invoke(view) }
        Assert.assertEquals(viewHolder, result)
    }

    @Test
    fun `should register factory using safe method`() {
        emptyRegistry.registerSafe(RecyclerItem::class, TEST_LAYOUT_ID_A, factoryMethodA)
        val result = emptyRegistry.create(view, TEST_LAYOUT_ID_A)

        verify(exactly = 1) { factoryMethodA.invoke(view) }
        Assert.assertEquals(viewHolder, result)
    }

    @Test
    fun `should merge two registries into new one`() {
        val newRegistry = registryA + registryB

        newRegistry.create(view, TEST_LAYOUT_ID_A)
        newRegistry.create(view, TEST_LAYOUT_ID_B)

        verifyOrder {
            factoryMethodA.invoke(view)
            factoryMethodB.invoke(view)
        }

        Assert.assertNotSame(registryB, newRegistry)
        Assert.assertNotSame(registryA, newRegistry)
    }

    @Test
    fun `should create new registry as a result of plusAssign`() {
        val oldA = registryA
        registryA += registryB

        registryA.create(view, TEST_LAYOUT_ID_A)
        registryA.create(view, TEST_LAYOUT_ID_B)

        verifyOrder {
            factoryMethodA.invoke(view)
            factoryMethodB.invoke(view)
        }
        Assert.assertNotSame(oldA, registryA)
    }
}