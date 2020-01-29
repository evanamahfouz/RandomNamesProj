package com.example.randomnamesproj.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.randomnamesproj.data.model.VolumeInfo

@Entity(tableName = "nameRandom_tb")
data class RandomNameEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "subtitle") var subtitle: String?,
    @ColumnInfo(name = "authors") var authors: String,
    @ColumnInfo(name = "description") var description: String?



) {
    fun mapToVolumInfo(): VolumeInfo {
        return VolumeInfo(title, subtitle, authors.split(','), null, null, description)
    }
}