package com.example.randomnamesproj.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomnamesproj.data.model.Example


@Entity(tableName = "MaleName_tb")
data class MaleNameEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "surname") var surname: String,
    @ColumnInfo(name = "region") var region: String


) {

    fun mapToExample(): Example {
        return Example(name, surname, gender = "female", region = region)
    }
}