package com.example.randomnamesproj.data.db

import androidx.room.*

@Dao
interface RandomNameDOA {
    @Query("SELECT * FROM RandomName_tb where gender LIKE  :gender1")
    suspend fun getAll(gender1: String): List<RandomNameEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(data: List<RandomNameEntity>)

    @Query("SELECT COUNT(*) FROM RandomName_tb")
    suspend fun getCount(): Int

    @Delete
    suspend fun delete(data: RandomNameEntity)
}