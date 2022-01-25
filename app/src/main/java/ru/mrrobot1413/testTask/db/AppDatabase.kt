package ru.mrrobot1413.testTask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mrrobot1413.testTask.data.GetPostsResponseData

@Database(entities = [GetPostsResponseData::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cacheDao(): CacheDao
}