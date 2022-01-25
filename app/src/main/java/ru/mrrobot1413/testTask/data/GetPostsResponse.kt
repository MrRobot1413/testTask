package ru.mrrobot1413.testTask.data

data class GetPostsResponse(
    val code: Int,
    val data: List<GetPostsResponseData>
)

data class GetPostsResponseData(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)