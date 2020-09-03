package com.github.rmielnik.list

/**
 * Represents an object that can generate update payload by comparing itself with an object in
 * up-to-date state.
 *
 * [getUpdatePayload] is called by [SimpleDiffCallback.getChangePayload].
 *
 * @see SimpleDiffCallback
 */
interface UpdatableItem {

    /**
     * Creates update payload. Called by [SimpleDiffCallback.getChangePayload]
     */
    fun getUpdatePayload(newItem: Any): Any?
}