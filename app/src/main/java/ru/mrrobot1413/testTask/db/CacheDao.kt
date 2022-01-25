package ru.mrrobot1413.testTask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mrrobot1413.testTask.data.GetPostsResponseData

@Dao
interface CacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<GetPostsResponseData>)

    @Query("SELECT * FROM cacheTable WHERE page=:page")
    suspend fun selectAll(page: Int): List<GetPostsResponseData>
}