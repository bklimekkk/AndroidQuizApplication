/**
 * Represents screen which enables users specify right answer for question they created
 * (from the list of answer options they created earlier).
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidquizapplication.databinding.FragmentSpecifyRightAnswerBinding
import com.example.androidquizapplication.entities.Question
import com.example.androidquizapplication.models.QuizViewModel


class SpecifyRightAnswer : Fragment() {

    private lateinit var specifyRightAnswerBinding: FragmentSpecifyRightAnswerBinding
    private val args by navArgs<SpecifyRightAnswerArgs>()
    private val viewModel: QuizViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        specifyRightAnswerBinding = FragmentSpecifyRightAnswerBinding.inflate(
            inflater, container,
            false
        )

        prepareScreen()

        specifyRightAnswerBinding.cancelOperationButton.setOnClickListener {
            val transition =
                SpecifyRightAnswerDirections.actionSpecifyRightAnswerToQuestions(args.bankName)
            findNavController().navigate(transition)
        }

        specifyRightAnswerBinding.finishButton.setOnClickListener {
            addQuestion()
            val transition = SpecifyRightAnswerDirections.actionSpecifyRightAnswerToQuestions(
                args.bankName
            )
            findNavController().navigate(transition)
        }
        return specifyRightAnswerBinding.root
    }

    /**
     * Function is responsible for adding a question to table of questions in the database.
     */
    private fun addQuestion() {
        var listOfOptions = mutableListOf<String>()

        for (i in 0..9) {
            if (args.listOfOptions.size - 1 >= i) {
                listOfOptions.add(args.listOfOptions[i])
            } else listOfOptions.add("")
        }

        val question = Question(
            0,
            args.bankName,
            args.question,
            listOfOptions[0],
            listOfOptions[1],
            listOfOptions[2],
            listOfOptions[3],
            listOfOptions[4],
            listOfOptions[5],
            listOfOptions[6],
            listOfOptions[7],
            listOfOptions[8],
            listOfOptions[9],
            specifyRightAnswerBinding.optionsSpinner.selectedItem.toString()
        )

        viewModel.addQuestion(question)
    }

    /**
     * Function is responsible for preparing window containing all question informations.
     */
    private fun prepareScreen() {
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Specify right answer"
        specifyRightAnswerBinding.questionConfirmation.text = args.question
        specifyRightAnswerBinding.optionsSpinner.adapter =
            ArrayAdapter(requireContext(), R.layout.simple_list_item_1, args.listOfOptions)
    }
}