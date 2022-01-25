package ru.mrrobot1413.testTask.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.mrrobot1413.testTask.data.GetPostsResponseData
import ru.mrrobot1413.testTask.db.AppDatabase
import ru.mrrobot1413.testTask.db.CacheDao
import ru.mrrobot1413.testTask.ui.home.HomeCachePagingSource
import ru.mrrobot1413.testTask.ui.home.HomePagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheRepository @Inject constructor(
    private val cacheDao: CacheDao
) {

    suspend fun insertAll(list: List<GetPostsResponseData>) {
        cacheDao.insertAll(list)
    }

    suspend fun selectAll(): Flow<PagingData<GetPostsResponseData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 40,
                initialLoadSize = 40
            ),
            pagingSourceFactory = {
                HomeCachePagingSource(cacheDao)
            }
        ).flow
    }
}