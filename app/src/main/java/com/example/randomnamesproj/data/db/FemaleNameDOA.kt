package com.example.randomnamesproj.data.db

import androidx.room.*

@Dao
interface FemaleNameDOA {
    @Query("SELECT * FROM FemaleName_tb")
    fun getAll(): List<FemaleNameEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<FemaleNameEntity>)


    @Delete
    fun delete(data: FemaleNameEntity)


}