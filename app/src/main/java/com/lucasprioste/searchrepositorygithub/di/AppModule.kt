package com.lucasprioste.searchrepositorygithub.di

import com.lucasprioste.searchrepositorygithub.BuildConfig
import com.lucasprioste.searchrepositorygithub.core.network_api_help.GitHubApiAuthenticator
import com.lucasprioste.searchrepositorygithub.data.remote.GitHubApi
import com.lucasprioste.searchrepositorygithub.data.repository.GitHubRepositoryImp
import com.lucasprioste.searchrepositorygithub.domain.repository.GitHubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGitHubApi(): GitHubApi{
        val okHttpClient = OkHttpClient.Builder()
            .authenticator(GitHubApiAuthenticator())
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.GIT_HUB_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesGitHubRepository(api: GitHubApi): GitHubRepository{
        return GitHubRepositoryImp(api = api)
    }

}