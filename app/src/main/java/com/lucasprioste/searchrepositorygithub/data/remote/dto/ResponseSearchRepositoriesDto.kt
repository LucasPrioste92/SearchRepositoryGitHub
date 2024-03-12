package com.lucasprioste.searchrepositorygithub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseSearchRepositoriesDto(
    @SerializedName("businesses")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<RepositoryDto>,
)
