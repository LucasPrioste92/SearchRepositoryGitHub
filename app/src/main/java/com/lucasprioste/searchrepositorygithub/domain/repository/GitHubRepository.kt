package com.lucasprioste.searchrepositorygithub.domain.repository

import com.lucasprioste.searchrepositorygithub.core.Resource
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.ResponseSearchRepositories
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    suspend fun searchRepositories(
        query: String,
        perPage: Int? = null,
        page: Int? = null,
        order: String? = null,
        sort: String? = null,
    ): Flow<Resource<ResponseSearchRepositories>>
}