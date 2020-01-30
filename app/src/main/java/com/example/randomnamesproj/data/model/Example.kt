package com.example.randomnamesproj.data.model

import com.example.randomnamesproj.data.db.FemaleNameEntity
import com.example.randomnamesproj.data.db.MaleNameEntity

data class Example(var name: String, var surname: String, var gender: String, var region: String) {

    fun mapToFemaleName(): FemaleNameEntity {
        return FemaleNameEntity(0, name, surname, region)

    }

    fun mapToMaleName(): MaleNameEntity {
        return MaleNameEntity(0, name, surname, region)

    }

}





