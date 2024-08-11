package com.shivayogi.galaxygaze.domain.usecase

import com.shivayogi.galaxygaze.data.local.entities.ApodEntity
import com.shivayogi.galaxygaze.data.repositories.ApodRepository
import javax.inject.Inject

class GetApodUseCase @Inject constructor(
    private val repository: ApodRepository
) {
    suspend operator fun invoke(date: String): ApodEntity? {
        return repository.getApod(date)
    }
}

