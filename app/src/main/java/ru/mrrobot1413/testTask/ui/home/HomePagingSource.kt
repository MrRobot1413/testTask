package ru.mrrobot1413.testTask.ui.home

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.mrrobot1413.testTask.data.GetPostsResponse
import ru.mrrobot1413.testTask.data.GetPostsResponseData
import ru.mrrobot1413.testTask.db.CacheDao
import ru.mrrobot1413.testTask.network.Api
import java.io.IOException

class HomePagingSource(
    private val api: Api,
    private val cacheDao: CacheDao
) : PagingSource<Int, GetPostsResponseData>() {
    override fun getRefreshKey(state: PagingState<Int, GetPostsResponseData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GetPostsResponseData> {
        val position = params.key ?: 1

        return try {
            val apiRequest = api.getPosts(position)
            val list = mutableListOf<GetPostsResponseData>()
            apiRequest.data.map {
                it.page = apiRequest.meta.pagination.page
                list.add(it)
            }
            cacheDao.insertAll(list)
            val data = cacheDao.selectAll(position)
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            val data = cacheDao.selectAll(position)
            LoadResult.Page(
                data = data,
                prevKey = null,
                nextKey = if (data.isEmpty()) null else position + 1
            )
        } catch (e: HttpException) {
            Log.d("Exception", "Ex: ${e.message.toString()}")
            LoadResult.Error(e)
        }
    }

}