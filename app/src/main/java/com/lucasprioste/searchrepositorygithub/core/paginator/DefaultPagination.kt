package com.lucasprioste.searchrepositorygithub.core.paginator


import com.lucasprioste.searchrepositorygithub.core.network_api_help.Resource
import kotlinx.coroutines.flow.Flow

class DefaultPagination<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Flow<Resource<List<Item>>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (message: String?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
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