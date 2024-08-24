package com.example.jetpack_demo_test.domain.usecase


import com.example.jetpack_demo_test.domain.model.PetDetails
import com.example.jetpack_demo_test.domain.repository.PetsRepository
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetPetInfoUseCase
    @Inject
    constructor(
        private val petsRepository: PetsRepository,
    ) : UseCase<GetPetInfoUseCase.Params, Any>(Dispatchers.IO) {
        override suspend fun execute(params: Params): PetDetails = petsRepository.getData()

        data class Params(
            val name: String,
        )
    }
