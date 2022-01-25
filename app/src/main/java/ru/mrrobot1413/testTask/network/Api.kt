package ru.mrrobot1413.testTask.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.mrrobot1413.testTask.data.GetPostsResponse

interface Api {
    @GET("v1/posts")
    suspend fun getPosts(@Query("page") page: Int = 1): GetPostsResponse
}