package ru.mrrobot1413.testTask.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class GetPostsResponse(
    @SerializedName("data")
    val data: List<GetPostsResponseData>,
    @SerializedName("meta")
    val meta: GetPostsResponseMeta
)

data class GetPostsResponseMeta(
    @SerializedName("pagination")
    val pagination: GetPostsResponseMetaPagination
)

data class GetPostsResponseMetaPagination(
    @SerializedName("page")
    val page: Int
)

@Entity(tableName = "cacheTable")
data class GetPostsResponseData(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String,
    @SerializedName("body")
    @ColumnInfo(name = "body")
    val body: String,
    @ColumnInfo(name = "page")
    var page: Int
)