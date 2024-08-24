package com.example.jetpack_demo_test.di

import com.example.jetpack_demo_test.data.PetsDataSource
import com.example.jetpack_demo_test.data.PetsDataSourceImpl
import com.example.jetpack_demo_test.domain.repository.PetsRepository
import com.example.jetpack_demo_test.domain.repository.PetsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun providePetsDataSource(petsDataSourceImpl: PetsDataSourceImpl): PetsDataSource

    @Binds
    abstract fun providePetsRepository(petsRepositoryImpl: PetsRepositoryImpl): PetsRepository
}
