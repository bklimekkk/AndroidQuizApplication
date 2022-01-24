package com.example.androidquizapplication.repositories

import androidx.lifecycle.LiveData
import com.example.androidquizapplication.daos.QuizDao
import com.example.androidquizapplication.entities.Bank
import com.example.androidquizapplication.entities.Question

class QuizRepository(private val quizDao: QuizDao) {
    val getAllBanks: LiveData<List<Bank>> = quizDao.getAllBanks()

    val getListOfBanks: List<Bank> = quizDao.getListOfBanks()

    fun getAllQuestions(bankName: String) = quizDao.getAllQuestions(bankName)

    fun getAllQuizQuestions(bankName: String) = quizDao.getAllQuizQuestions(bankName)
    suspend fun addBank(bank: Bank) {
        quizDao.addBank(bank)
    }

    suspend fun deleteBank(bankName: String) {
        quizDao.deleteBank(bankName)
        quizDao.deleteAllQuestions(bankName)
    }

    suspend fun addQuestion(question: Question) {
        quizDao.addQuestion(question)
    }

    suspend fun deleteQuestion(question: String, bankName: String) {
        quizDao.deleteSingleQuestion(question, bankName)
    }

    suspend fun updateQuestion(oldQuestion: String, bankName: String, newQuestion: String) {
        quizDao.updateSingleQuestion(oldQuestion, bankName, newQuestion)
    }
}