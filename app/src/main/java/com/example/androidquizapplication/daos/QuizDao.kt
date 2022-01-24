package com.example.androidquizapplication.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidquizapplication.entities.Bank
import com.example.androidquizapplication.entities.Question

@Dao
interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBank(bank: Bank)

    @Query("SELECT * FROM banks ORDER BY id ASC")
    fun getAllBanks(): LiveData<List<Bank>>

    @Query("SELECT * FROM banks")
    fun getListOfBanks(): List<Bank>

    @Query("DELETE FROM banks WHERE name = :name")
    suspend fun deleteBank(name: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addQuestion(question: Question)

    @Query("SELECT * FROM questions WHERE bankName = :bankName ORDER BY id ASC")
    fun getAllQuestions(bankName: String): LiveData<List<Question>>

    @Query("SELECT * FROM questions WHERE bankName = :bankName ORDER BY id ASC")
    fun getAllQuizQuestions(bankName: String): List<Question>

    @Query("DELETE FROM questions WHERE bankName = :bankName")
    suspend fun deleteAllQuestions(bankName: String)

    @Query("DELETE FROM questions WHERE question = :question AND bankName = :bankName")
    suspend fun deleteSingleQuestion(question: String, bankName: String)

    @Query("UPDATE questions SET question = :newQuestion WHERE bankName = :bankName AND question = :oldQuestion")
    suspend fun updateSingleQuestion(oldQuestion: String, bankName: String, newQuestion: String)
}