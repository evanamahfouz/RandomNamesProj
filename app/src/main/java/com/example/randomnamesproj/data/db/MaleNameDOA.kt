package com.example.randomnamesproj.data.db

import androidx.room.*


@Dao
interface MaleNameDOA {
    @Query("SELECT * FROM MaleName_tb")
    fun getAll(): List<FemaleNameEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<FemaleNameEntity>)


    @Delete
    fun delete(data: FemaleNameEntity)

}