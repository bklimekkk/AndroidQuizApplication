/**
 * Represents screen which enables users to create new bank.
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.androidquizapplication.databinding.FragmentTeacherNewBankBinding
import com.example.androidquizapplication.entities.Bank
import com.example.androidquizapplication.models.QuizViewModel
import com.google.android.material.snackbar.Snackbar

class TeacherNewBank : Fragment() {
    private lateinit var teacherNewBankBinding: FragmentTeacherNewBankBinding
    private lateinit var bankName: String
    private lateinit var snackbar: Snackbar
    private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherNewBankBinding = FragmentTeacherNewBankBinding.inflate(
            inflater, container,
            false
        )

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Create new bank"

        teacherNewBankBinding.newBankBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_teacherNewBank_to_teacherBanks)
        }

        teacherNewBankBinding.createBankButton.setOnClickListener {
            bankName = teacherNewBankBinding.bankNameInputField.text.toString()
            createBank(it)
        }

        return teacherNewBankBinding.root
    }

    /**
     * Function is responsible for adding bank to the table of banks in the database.
     * [it] is the current view.
     */
    private fun createBank(it: View) {
        if (bankName != "" && !bankExists(bankName)) {
            val bank = Bank(0, bankName)
            viewModel.addBank(bank)
            teacherNewBankBinding.bankNameInputField.setText("")
            findNavController().navigate(R.id.action_teacherNewBank_to_teacherBanks)
        } else if (bankExists(bankName)) {
            showSnackBar(it, "Bank with this name already exists")
        } else {
            showSnackBar(it, "You have to give bank a name")
        }
    }

    /**
     * Function is responsible for checking if banks' names duplicate. If yes, users aren't able to
     * add bank with the same name.
     * [bankName] is the name of the bank that is checked.
     * @return true if table with bank contains bank with the same name, false if not.
     */
    private fun bankExists(bankName: String): Boolean {
        var banksTable = viewModel.getListOfBanks
        var listOfBanks = mutableListOf<String>()

        banksTable.forEach {
            listOfBanks.add(it.name)
        }
        return listOfBanks.contains(bankName)
    }

    /**
     * Function shows snackbar in the bottom of the screen.
     * [it] is current view.
     * [message] is text contained in the snackbar.
     */
    private fun showSnackBar(it: View, message: String) {
        snackbar = Snackbar.make(it, message, Snackbar.LENGTH_LONG)
        hideKeyboard(it)
        snackbar.setAction("Hide") {
            snackbar.dismiss()
        }
        snackbar.show()
    }

    /**
     * Function hides keyboard.
     * [view] is current view.
     */
    private fun hideKeyboard(view: View) {
        val im: InputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        im.hideSoftInputFromWindow(view.windowToken, 0)
    }
}