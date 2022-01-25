package ru.mrrobot1413.testTask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mrrobot1413.testTask.network.Api
import ru.mrrobot1413.testTask.repository.PostsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun providePostsRepository(api: Api): PostsRepository {
        return PostsRepository(api)
    }
}