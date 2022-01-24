package com.example.androidquizapplication.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidquizapplication.database.QuizRoomDatabase
import com.example.androidquizapplication.entities.Bank
import com.example.androidquizapplication.entities.Question
import com.example.androidquizapplication.repositories.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizViewModel(application: Application): AndroidViewModel(application) {
    val getAllBanks: LiveData<List<Bank>>
    val getListOfBanks: List<Bank>
    private val repository: QuizRepository

    init {
        val quizDao = QuizRoomDatabase.getDatabase(application)!!.bankDao()
        repository = QuizRepository(quizDao)
        getAllBanks = repository.getAllBanks
        getListOfBanks = repository.getListOfBanks
    }

    fun addBank(bank: Bank) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBank(bank)
        }
    }

    fun deleteBank(bankName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBank(bankName)
        }
    }

    fun getAllQuestions(bankName: String): LiveData<List<Question>> {
        return repository.getAllQuestions(bankName)
    }

    fun getAllQuizQuestions(bankName: String): List<Question> {
        return repository.getAllQuizQuestions(bankName)
    }

    fun addQuestion(question: Question) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addQuestion(question)
        }
    }

    fun deleteQuestion(question: String, bankName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteQuestion(question, bankName)
        }
    }

    fun updateQuestion(oldQuestion: String, bankName: String, newQuestion: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateQuestion(oldQuestion, bankName, newQuestion)
        }
    }

}