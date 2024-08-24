package com.example.jetpack_demo_test.domain.repository

import com.example.jetpack_demo_test.data.PetsDataSource
import com.example.jetpack_demo_test.domain.model.Animal
import com.example.jetpack_demo_test.domain.model.Breeds
import com.example.jetpack_demo_test.domain.model.PetDetails
import javax.inject.Inject

class PetsRepositoryImpl
    @Inject
    constructor(
        private val petsDataSource: PetsDataSource,
    ) : PetsRepository {
        override suspend fun getData(): PetDetails = petsDataSource.getData()

        override suspend fun getBreedsListFor(
            animal: Animal,
            icon: Int,
        ): List<Breeds> = petsDataSource.getBreedsListFor(animal, icon)

        override suspend fun filterList(
            index: Int,
            input: String,
        ) = petsDataSource.filterList(index, input)

        override suspend fun getStatisticCount(breeds: List<Breeds>): List<Map.Entry<Char, Int>> = petsDataSource.getStatisticCount(breeds)
    }
