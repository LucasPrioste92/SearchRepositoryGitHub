package com.lucasprioste.searchrepositorygithub.data.repository

import com.lucasprioste.searchrepositorygithub.core.Resource
import com.lucasprioste.searchrepositorygithub.data.mapper.toResponseSearchRepositories
import com.lucasprioste.searchrepositorygithub.data.remote.GitHubApi
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Repository
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.ResponseSearchRepositories
import com.lucasprioste.searchrepositorygithub.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GitHubRepositoryImp @Inject constructor(
    private val api: GitHubApi
): GitHubRepository {
    override suspend fun searchRepositories(
        query: String,
        perPage: Int?,
        page: Int?,
        order: String?,
        sort: String?
    ): Flow<Resource<List<Repository>>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            val response = api.getSearchRepositories(
                query = query,
                perPage = perPage,
                page = page,
                order = order,
                sort = sort
            )
            emit(Resource.Success(response.toResponseSearchRepositories().items))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(message = e.message ?: "Something Went Wrong: ${e.message()}"))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(Resource.Error(message = e.message ?: "Request Error: ${e.message}"))
        }
        emit(Resource.Loading(isLoading = false))
    }
}