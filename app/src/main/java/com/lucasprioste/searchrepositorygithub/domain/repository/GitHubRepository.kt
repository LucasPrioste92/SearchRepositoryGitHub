package com.lucasprioste.searchrepositorygithub.domain.repository

import com.lucasprioste.searchrepositorygithub.core.network_api_help.Resource
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Repository
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    suspend fun searchRepositories(
        query: String,
        perPage: Int? = null,
        page: Int? = null,
        order: String? = null,
        sort: String? = null,
    ): Flow<Resource<List<Repository>>>
}