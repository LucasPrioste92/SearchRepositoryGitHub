package com.lucasprioste.searchrepositorygithub.core.paginator


import com.lucasprioste.searchrepositorygithub.core.network_api_help.Resource
import kotlinx.coroutines.flow.Flow

class DefaultPagination<Key, Item>(
    private val initialKey: Key,
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextKey: Key) -> Flow<Resource<List<Item>>>,
    private val getNextKey: suspend (List<Item>) -> Key,
    private val onError: suspend (message: String?) -> Unit,
    private val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Pagination<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }

        onRequest(currentKey).collect{ response ->
            when(response){
                is Resource.Error -> {
                    onError(response.message)
                }
                is Resource.Loading -> {
                    onLoadUpdated(response.isLoading)
                    isMakingRequest = response.isLoading
                }
                is Resource.Success -> {
                    response.data?.let {
                        currentKey = getNextKey(it)
                        onSuccess(it, currentKey)
                    }
                }
            }
        }
    }

    override fun reset(key: Key?) {
        currentKey = key ?: initialKey
    }
}