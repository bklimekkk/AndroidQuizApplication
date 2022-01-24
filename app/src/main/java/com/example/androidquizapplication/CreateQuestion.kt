/**
 * Represents screen which enables users to enter text of question they currently create.
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
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidquizapplication.databinding.FragmentCreateQuestionBinding
import com.google.android.material.snackbar.Snackbar

class CreateQuestion : Fragment() {
    private val args by navArgs<CreateQuestionArgs>()
    private lateinit var createQuestionBinding: FragmentCreateQuestionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createQuestionBinding = FragmentCreateQuestionBinding.inflate(
            inflater, container,
            false
        )

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Create question"

        createQuestionBinding.cancelButton.setOnClickListener {
            val transition = CreateQuestionDirections.actionCreateQuestionToQuestions(args.bankName)
            findNavController().navigate(transition)
        }

        //Users can't proceed to next window until they provide question.
        createQuestionBinding.confirmButton.setOnClickListener {
            if (createQuestionBinding.question.text.toString() != "") {
                goToOptionsString()
            } else {
               showSnackbar(it, "Enter question")
            }
        }
        return createQuestionBinding.root
    }

    /**
     * Function takes users to window in which they are going to be able to add options of
     * answering the question.
     */
    private fun goToOptionsString() {
        val transition = CreateQuestionDirections.actionCreateQuestionToCreateOptions(
            createQuestionBinding.question.text.toString(), args.bankName
        )
        findNavController().navigate(transition)
    }

    /**
     * Function shows snackbar in the bottom of the screen.
     * [it] is current view.
     * [message] is text contained in the snackbar.
     */
    private fun showSnackbar(it: View, message: String) {
        var snackbar = Snackbar.make(it, message, Snackbar.LENGTH_LONG)
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