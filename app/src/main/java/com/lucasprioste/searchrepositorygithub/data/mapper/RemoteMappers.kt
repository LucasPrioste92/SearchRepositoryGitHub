package com.lucasprioste.searchrepositorygithub.data.mapper

import com.lucasprioste.searchrepositorygithub.data.remote.dto.OwnerDto
import com.lucasprioste.searchrepositorygithub.data.remote.dto.RepositoryDto
import com.lucasprioste.searchrepositorygithub.data.remote.dto.ResponseSearchRepositoriesDto
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Owner
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.Repository
import com.lucasprioste.searchrepositorygithub.domain.model.repositories_git.ResponseSearchRepositories
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun ResponseSearchRepositoriesDto.toResponseSearchRepositories(): ResponseSearchRepositories{
    return ResponseSearchRepositories(
        totalCount = totalCount,
        incompleteResults = incompleteResults,
        items = items.map { it.toRepository() }
    )
}

fun RepositoryDto.toRepository(): Repository{
    return Repository(
        id = id,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toOwner(),
        createdAt = createdAt.toLocalDateTime(),
        updatedAt = updatedAt.toLocalDateTime(),
        description = description,
        fork = fork,
        forksCount = forksCount,
        language = language,
        openIssuesCount = openIssuesCount,
        score = score,
        watchersCount = watchersCount,
    )
}

fun OwnerDto.toOwner(): Owner{
    return Owner(
        name = login,
        imageUrl = avatarUrl
    )
}

fun String.toLocalDateTime(): LocalDateTime{
    return try {
        LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-ddThh:mm:ssZ"))
    } catch (e: Exception){
        LocalDateTime.now()
    }
}