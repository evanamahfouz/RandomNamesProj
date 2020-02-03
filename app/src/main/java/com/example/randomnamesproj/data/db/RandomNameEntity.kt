package com.example.randomnamesproj.data.db

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomnamesproj.data.model.RandomName

@Entity(tableName = "RandomName_tb")
data class RandomNameEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "surname") var surname: String = "",
    @ColumnInfo(name = "gender") var gender: String = "",

    @ColumnInfo(name = "region") var region: String = ""


) {
    fun mapToExample(gender: String): RandomName {
        return RandomName(name, surname, gender = gender, region = region)
    }
}