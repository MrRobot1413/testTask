package ru.mrrobot1413.testTask.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ru.mrrobot1413.testTask.data.GetPostsResponse
import ru.mrrobot1413.testTask.data.GetPostsResponseData
import ru.mrrobot1413.testTask.db.CacheDao
import ru.mrrobot1413.testTask.network.Api
import ru.mrrobot1413.testTask.ui.home.HomePagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepository @Inject constructor(
    private val api: Api,
    private val cacheDao: CacheDao
) {

    fun getPosts(): Flow<PagingData<GetPostsResponseData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 20,
                initialLoadSize = 40
            ),
            pagingSourceFactory = {
                HomePagingSource(api, cacheDao)
            }
        ).flow
    }
}