package com.lucasprioste.searchrepositorygithub.core.paginator

interface Pagination<Key, Item> {
    suspend fun loadNextItems()
    fun reset(key: Key?)
}