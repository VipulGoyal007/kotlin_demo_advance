package com.example.jetpack_demo_test.data

import com.example.jetpack_demo_test.domain.model.Animal
import com.example.jetpack_demo_test.domain.model.Breeds
import com.example.jetpack_demo_test.domain.model.PetDetails

interface PetsDataSource {
    suspend fun getData(): PetDetails

    suspend fun getBreedsListFor(
        animal: Animal,
        icon: Int,
    ): List<Breeds>

    suspend fun filterList(
        index: Int,
        input: String,
    ): PetDetails

    suspend fun getStatisticCount(breeds: List<Breeds>): List<Map.Entry<Char, Int>>
}
