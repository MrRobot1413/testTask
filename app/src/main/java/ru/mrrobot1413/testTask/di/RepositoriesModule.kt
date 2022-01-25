package ru.mrrobot1413.testTask.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.mrrobot1413.testTask.db.AppDatabase
import ru.mrrobot1413.testTask.db.CacheDao
import ru.mrrobot1413.testTask.network.Api
import ru.mrrobot1413.testTask.repository.CacheRepository
import ru.mrrobot1413.testTask.repository.PostsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun providePostsRepository(api: Api, cacheDao: CacheDao): PostsRepository {
        return PostsRepository(api, cacheDao)
    }

    @Provides
    @Singleton
    fun provideCacheRepository(cacheDao: CacheDao): CacheRepository {
        return CacheRepository(cacheDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "qaDb").build()
    }

    @Provides
    @Singleton
    fun provideWordDao(database: AppDatabase): CacheDao {
        return database.cacheDao()
    }
}