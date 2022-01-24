package com.example.androidquizapplication.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "questions")
data class Question (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,
    var bankName: String = "",
    var question: String = "",
    var firstOption: String = "",
    var secondOption: String = "",
    var thirdOption: String = "",
    var fourthOption: String = "",
    var fifthOption: String = "",
    var sixthOption: String = "",
    var seventhOption: String = "",
    var eightOption: String = "",
    var ninthOption: String = "",
    var tenthOption: String = "",
    var rightOption: String = ""
)
