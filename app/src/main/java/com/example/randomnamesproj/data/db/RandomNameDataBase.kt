package com.example.randomnamesproj.data.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [RandomNameEntity::class],
    version = 1

)
abstract class RandomNameDataBase : RoomDatabase() {
    abstract fun randomNameDOA(): RandomNameDOA
}