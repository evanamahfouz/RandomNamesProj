package com.example.randomnamesproj.data.model

import android.os.Parcel
import android.os.Parcelable

import com.example.randomnamesproj.data.db.RandomNameEntity

data class RandomName(
    var name: String,
    var surname: String,
    var gender: String,
    var region: String
) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )


    fun mapToRandomName(): RandomNameEntity {

        return RandomNameEntity(name = name, surname = surname, gender = gender, region = region)

    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(surname)
        parcel.writeString(gender)
        parcel.writeString(region)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RandomName> {
        override fun createFromParcel(parcel: Parcel): RandomName {
            return RandomName(parcel)
        }

        override fun newArray(size: Int): Array<RandomName?> {
            return arrayOfNulls(size)
        }
    }

}





