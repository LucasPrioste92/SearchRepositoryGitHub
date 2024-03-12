package com.lucasprioste.searchrepositorygithub.core.network_api_help

import com.lucasprioste.searchrepositorygithub.BuildConfig
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class GitHubApiAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.GIT_HUB_API_TOKEN}")
            .build()
    }
}