package com.example.jetpack_demo_test.domain.usecase


import com.example.jetpack_demo_test.domain.model.Breeds
import com.example.jetpack_demo_test.domain.repository.PetsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetStatisticCountUseCase
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
    ) : UseCase<GetStatisticCountUseCase.Params, Any>(Dispatchers.IO) {
        override suspend fun execute(params: Params) = petsRepository.getStatisticCount(params.breeds)

        data class Params(
            val breeds: List<Breeds>,
        )
    }
