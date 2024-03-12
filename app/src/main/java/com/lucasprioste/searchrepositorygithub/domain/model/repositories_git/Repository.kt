package com.lucasprioste.searchrepositorygithub.domain.model.repositories_git

import java.time.LocalDateTime

data class Repository(
    val id: Int,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val owner: Owner,
    val description: String,
    val fork: Boolean,
    val language: String,
    val score: Int,
    val watchersCount: Int,
    val openIssuesCount: Int,
    val forksCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)