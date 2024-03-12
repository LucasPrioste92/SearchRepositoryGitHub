package com.lucasprioste.searchrepositorygithub.data.remote

import com.lucasprioste.searchrepositorygithub.data.remote.dto.ResponseSearchRepositoriesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {
    @GET("search/repositories")
    suspend fun getSearchRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int? = null,
        @Query("page") page: Int? = null,
        @Query("order") order: String? = null,
        @Query("sort") sort: String? = null,
    ): ResponseSearchRepositoriesDto
}