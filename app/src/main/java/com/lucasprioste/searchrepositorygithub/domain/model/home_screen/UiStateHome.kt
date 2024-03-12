package com.lucasprioste.searchrepositorygithub.domain.model.home_screen

import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Repository

data class UiStateHome(
    val repositories: List<Repository> = emptyList(),
    val isLoading: Boolean = true,
    val errorStringResource: Int? = null
)
