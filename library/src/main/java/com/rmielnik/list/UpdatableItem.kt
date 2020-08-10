package com.rmielnik.list

/**
 * Represents item that is able to create update payload from new item state.
 */
interface UpdatableItem {

    /**
     * Creates update payload.
     */
    fun getUpdatePayload(newItem: Any): Any?
}