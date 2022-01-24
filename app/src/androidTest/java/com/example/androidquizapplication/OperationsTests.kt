package com.example.androidquizapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.androidquizapplication.daos.QuizDao
import com.example.androidquizapplication.database.Injection
import com.example.androidquizapplication.database.QuizRoomDatabaseI
import com.example.androidquizapplication.entities.Question
import com.example.androidquizapplication.util.LiveDataTestUtil
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.androidquizapplication.util.TestUtil
import junit.framework.Assert.*

@RunWith(AndroidJUnit4::class)
class OperationsTests {

    @JvmField @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var quizDao: QuizDao
    private lateinit var database: QuizRoomDatabaseI
    private val testUtil = TestUtil()

    @Before
    @Throws(Exception::class)
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Injection.getDatabase(context)
        quizDao = database.bankDao()
    }

    @Test
    fun checkIfAddedBankExists() = runBlocking {
        val bank = testUtil.createBanks(1)
        quizDao.addBank(bank[0])
        val existingBanksList = quizDao.getListOfBanks()
        val banksNames = mutableListOf<String>()

        existingBanksList.forEach {
            banksNames.add(it.name)
        }

        assertTrue(banksNames.contains(bank[0].name))

        quizDao.deleteBank(bank[0].name)

    }

    @Test
    fun checkLengthOfBanksListAfterInsertingMultipleBanks() = runBlocking {
        val existingListSize = quizDao.getListOfBanks().size
        val banks = testUtil.createBanks(3)

        banks.forEach {
            quizDao.addBank(it)
        }

        val foundBanks = quizDao.getAllBanks()
        assertEquals(existingListSize + 3, LiveDataTestUtil.getValue(foundBanks).size)

        banks.forEach {
            quizDao.deleteBank(it.name)
        }
    }

    @Test
    fun checkBanksDeletion() = runBlocking {
        val bank = testUtil.createBanks(1)
        quizDao.addBank(bank[0])
        quizDao.deleteBank(bank[0].name)

        val existingBanksList = quizDao.getListOfBanks()
        val banksNames = mutableListOf<String>()

        existingBanksList.forEach {
            banksNames.add(it.name)
        }

        assertFalse(banksNames.contains(bank[0].name) || banksNames.size != existingBanksList.size)
    }

    @Test
    fun checkMultipleBanksDeletion() = runBlocking {
        val banks = testUtil.createBanks(3)

        banks.forEach {
            quizDao.addBank(it)
        }

        banks.forEach {
            quizDao.deleteBank(it.name)
        }

        val existingListSize = quizDao.getListOfBanks().size

        val foundBanks = quizDao.getAllBanks()
        assertEquals(existingListSize, LiveDataTestUtil.getValue(foundBanks).size)

        banks.forEach {
            quizDao.deleteBank(it.name)
        }
    }

    @Test
    fun checkIfAddedQuestionExists() = runBlocking {
        val bank = testUtil.createBanks(1)
        quizDao.addBank(bank[0])
        quizDao.addQuestion(Question(0, bank[0].name, "Question?", "", "",
        "", "", "", "", "", "", "",
            "", ""))

        val questionsList = quizDao.getAllQuizQuestions(bank[0].name)

        val questionsNamesList = mutableListOf<String>()

        questionsList.forEach {
            questionsNamesList.add(it.question)
        }

        assertTrue(questionsNamesList.contains("Question?"))

        quizDao.deleteSingleQuestion("Question?", bank[0].name)
        quizDao.deleteBank(bank[0].name)
    }

    @Test
    fun checkDeletingQuestion() = runBlocking {
        val bank = testUtil.createBanks(1)
        quizDao.addBank(bank[0])
        quizDao.addQuestion(Question(0, bank[0].name, "Question?", "", "",
            "", "", "", "", "", "", "",
            "", ""))
        quizDao.deleteSingleQuestion("Question?", bank[0].name)

        val questionsList = quizDao.getAllQuizQuestions(bank[0].name)

        val questionsNamesList = mutableListOf<String>()

        questionsList.forEach {
            questionsNamesList.add(it.question)
        }

        assertFalse(questionsNamesList.contains("Question?"))

        quizDao.deleteBank(bank[0].name)
    }
}