package com.example.randomnamesproj.data.db

import androidx.room.*

@Dao
interface RandomNameDOA {
    @Query("SELECT * FROM RandomName_tb where gender LIKE  :gender1")
     fun getAll(gender1: String): List<RandomNameEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(data: List<RandomNameEntity>)

    @Query("SELECT COUNT(*) FROM RandomName_tb")
     fun getCount(): Int

    @Delete
     fun delete(data: RandomNameEntity)

}