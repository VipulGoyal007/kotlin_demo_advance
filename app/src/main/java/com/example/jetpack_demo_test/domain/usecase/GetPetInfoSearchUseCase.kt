package com.example.jetpack_demo_test.domain.usecase

import com.example.jetpack_demo_test.domain.repository.PetsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPetInfoSearchUseCase
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
    ) : UseCase<GetPetInfoSearchUseCase.Params, Any>(Dispatchers.IO) {
        override suspend fun execute(params: Params) = petsRepository.filterList(params.index, params.input)

        data class Params(
            val index: Int,
            val input: String,
        )
    }
