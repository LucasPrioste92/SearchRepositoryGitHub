package com.lucasprioste.searchrepositorygithub.domain.model.repositories_git

data class ResponseSearchRepositories(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<Repository>,
)
