package com.example.jetpack_demo_test.domain.model

import androidx.annotation.DrawableRes

data class Species(
    val id: Animal,
    var breedsList: List<Breeds>,
    val petName: Int,
    @DrawableRes val image: Int,
)
