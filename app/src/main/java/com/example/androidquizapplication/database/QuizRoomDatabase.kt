package com.example.androidquizapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidquizapplication.daos.QuizDao
import com.example.androidquizapplication.entities.Bank
import com.example.androidquizapplication.entities.Question

@Database( entities = [Bank::class, Question::class], version = 1, exportSchema = false)
abstract class QuizRoomDatabase: RoomDatabase(), QuizRoomDatabaseI {

    abstract override fun bankDao(): QuizDao

    companion object {
        @Volatile
        private var instance: QuizRoomDatabase? = null


        fun getDatabase(context: Context): QuizRoomDatabase? {
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder (
                            context.applicationContext,
                            QuizRoomDatabase::class.java,
                            "quiz_database"
                        )
                            //.addMigrations(MIGRATION_1_2)
                            .allowMainThreadQueries()
                            .build()
                }
                return instance!!
            }
        }
//        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE IF NOT EXISTS 'questions' " +
//                        "('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                        "'bankName' STRING, 'question' STRING, 'firstOption' " +
//                        "STRING, 'secondOption' STRING, 'thirdOption' STRING, " +
//                        "'fourthOption' STRING, 'fifthOption' STRING, 'sixthOption' STRING, " +
//                        "'seventhOption' STRING, 'eightOption' STRING, 'ninthOption' STRING, " +
//                        "'tenthOption' STRING, 'rightOption' STRING)")
//            }
//        }
    }


}