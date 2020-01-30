package com.example.randomnamesproj.data.db

import androidx.room.*


@Dao
interface MaleNameDOA {
    @Query("SELECT * FROM MaleName_tb")
    fun getAll(): List<MaleNameEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<MaleNameEntity>)


    @Delete
    fun delete(data: MaleNameEntity)

}