package com.example.androidquizapplication.database

import com.example.androidquizapplication.daos.QuizDao

interface QuizRoomDatabaseI {
    fun bankDao(): QuizDao
}