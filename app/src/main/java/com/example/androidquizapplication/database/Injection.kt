package com.example.androidquizapplication.database

import android.content.Context

object Injection {
    fun getDatabase(context: Context): QuizRoomDatabaseI =
        QuizRoomDatabase.getDatabase(context)!!
}