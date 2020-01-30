package com.example.randomnamesproj.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [FemaleNameEntity::class, MaleNameEntity::class],
    version = 2

)
abstract class RandomNameDataBase : RoomDatabase() {
    abstract fun femaleNameDOA(): FemaleNameDOA
    abstract fun maleNameDOA(): MaleNameDOA

    companion object {
        @Volatile
        private var instance: RandomNameDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        fun getInstance(): RandomNameDataBase = instance!!

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            RandomNameDataBase::class.java, "random_name.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()
    }
}