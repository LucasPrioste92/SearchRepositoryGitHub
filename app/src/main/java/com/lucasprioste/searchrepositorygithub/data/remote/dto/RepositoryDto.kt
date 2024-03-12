package com.lucasprioste.searchrepositorygithub.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RepositoryDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("private")
    val private: Boolean,
    @SerializedName("owner")
    val owner: OwnerDto? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("fork")
    val fork: Boolean,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("score")
    val score: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)
