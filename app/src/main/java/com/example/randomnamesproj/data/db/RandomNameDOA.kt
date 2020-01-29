package com.example.randomnamesproj.data.db

import androidx.room.*

@Dao
interface RandomNameDOA {
    @Query("SELECT * FROM nameRandom_tb")
    fun getAll(): List<RandomNameEntity>

    @Query("SELECT * FROM nameRandom_tb WHERE title LIKE :title limit 1")
    fun findByTitle(title: String): RandomNameEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<RandomNameEntity>)


    @Delete
    fun delete(data: RandomNameEntity)

    @Update
    fun updateTodo(vararg data: RandomNameEntity)


}