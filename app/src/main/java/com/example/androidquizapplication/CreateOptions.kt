/**
 * Represents screen which enables users to create options for particular questions.
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import android.annotation.SuppressLint
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
import com.example.androidquizapplication.databinding.FragmentCreateOptionsBinding
import com.google.android.material.snackbar.Snackbar

 class CreateOptions : Fragment() {

    private lateinit var createOptionsBinding: FragmentCreateOptionsBinding
    private val args by navArgs<CreateOptionsArgs>()

    //Default initial number of options is equal to 1. Program ensures that user will add at least
    //one option.
    private var numberOfOptions = 1

    //Program will add options entered by user to this list.
    private var listOfOptions = mutableListOf<String>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createOptionsBinding = FragmentCreateOptionsBinding.inflate(
            inflater, container,
            false
        )

        createOptionsBinding.questionTextView.text = args.question

        //Title of the window is set.
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Create options"

        //Users can cancel operation of adding question at any time.
        createOptionsBinding.cancelOperation.setOnClickListener {
            val transition = CreateOptionsDirections.actionCreateOptionsToQuestions(args.bankName)
            findNavController().navigate(transition)
        }

        //By default, this button is meant for adding next option for particular Question.
        //However, there are circumstances in which option can't be added. Switch statement below
        //tells program what to do when these circumstances occur.
        createOptionsBinding.addOptionButton.setOnClickListener {

            var option = createOptionsBinding.optionField.text.toString()

            if (option == "") {
                showSnackbar(it, "Input box is empty")
            } else if (option == "No option selected") {
                showSnackbar(it, "This is default option for each question")
            } else if (listOfOptions.contains(option)) {
                showSnackbar(it, "This option already exists")
            } else if (listOfOptions.size < 10) {
                addNextOption(option)
            } else {
                createOptionsBinding.optionField.setText("")
                showSnackbar(it, "Maximum number of options: 10")
            }
        }

        //When this button is clicked and number of existing options is bigger than 1, process of
        //adding the question is finished and users are taken to different screen. If number of
        // options is less or equal to 1, users still need to add more options in order to create
        // a question.
        createOptionsBinding.nextButton.setOnClickListener {
            if (listOfOptions.size >= 2) {
                goToConfirmationScreen()
            } else {
                showSnackbar(it, "You have to add at least two options")
            }
        }
        return createOptionsBinding.root
    }

    /**
     * Function responsible for performing transition to next window in "adding question" operation.
     * (SpecifyRightAnswer)
     */
    private fun goToConfirmationScreen() {
        val transition = CreateOptionsDirections.actionCreateOptionsToSpecifyRightAnswer(
            createOptionsBinding.questionTextView.text.toString(),
            listOfOptions.toTypedArray(),
            args.bankName
        )
        findNavController().navigate(transition)
    }

    /**
     * Function responsible for adding next option to the list of all options.
     * [option] holds option entered by user.
     */
    private fun addNextOption(option: String) {
        listOfOptions.add(option)
        numberOfOptions++

        createOptionsBinding.optionField.setText("")

        var instruction: String = if (listOfOptions.size < 10) {
            "Enter option $numberOfOptions"
        } else {
            "Reached maximal number of options"
        }
        createOptionsBinding.optionField.hint = instruction
    }

    /**
     * Function shows snackbar in the bottom of the screen.
     * [it] is current view.
     * [message] is text contained in the snackbar.
     */
    private fun showSnackbar(it: View, message: String) {
        val snackbar = Snackbar.make(it, message, Snackbar.LENGTH_LONG)
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