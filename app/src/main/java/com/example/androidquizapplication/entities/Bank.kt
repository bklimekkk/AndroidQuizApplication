package com.example.androidquizapplication.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "banks")
data class Bank(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,
    var name: String = ""
)
