package com.example.jetpack_demo_test.domain.model

import androidx.annotation.DrawableRes

data class Breeds(
    val subTitle: String,
    val title: String,
    @DrawableRes val icon: Int,
)
