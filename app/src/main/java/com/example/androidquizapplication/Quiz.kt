/**
 * Represents screen which enables users to take chosen quiz.
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidquizapplication.databinding.FragmentQuizBinding
import com.example.androidquizapplication.entities.Question
import com.example.androidquizapplication.models.QuizViewModel
import androidx.appcompat.app.AppCompatActivity


class Quiz : Fragment() {
    private val viewModel: QuizViewModel by viewModels()
    private val args by navArgs<QuizArgs>()
    private lateinit var quizBinding: FragmentQuizBinding
    private lateinit var quizQuestions: List<Question>

    //Answers from database will be stored in this list.
    private var listOfAnswers = mutableListOf<String>()

    //These parameters are necessary for keeping track of right question to display
    //and number of correct answers during the quiz.
    private var questionNumber = 1
    private var numberOfQuestions = 0
    private var numberOfCorrectAnswers = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quizBinding = FragmentQuizBinding.inflate(inflater, container, false)

        //Users can exit the quiz at any time. In this case they won't see final result.
        quizBinding.finishQuiz.setOnClickListener {
            val transition = QuizDirections.actionQuizToQuizzes()
            findNavController().navigate(transition)
        }

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = args.bankName

        val bankName = args.bankName
        //Function used in the end of this line ensures that question will appear in random order.
        quizQuestions = viewModel.getAllQuizQuestions(bankName).shuffled()

        if (quizQuestions.isNotEmpty()) {

            setUpFirstQuestion()

            if(numberOfQuestions == 1) {
                quizBinding.nextQuestion.text = "Finish Quiz"
            }

            quizBinding.nextQuestion.setOnClickListener {
                verifyAnswer()

                //Program keeps displaying new questions as long as number of current question
                //is smaller than number of all quiz question.
                if (questionNumber < numberOfQuestions) {
                    if (questionNumber + 1 == numberOfQuestions) {
                        quizBinding.nextQuestion.text = "Finish Quiz"
                    }
                    askNextQuestion()
                } else {
                    finishQuiz()
                }
            }
        } else {
            informAboutEmptyBank()
        }
        return quizBinding.root
    }

    /**
     * Functions presents users with information that bank they entered is empty (=contains no
     * questions).
     */
    private fun informAboutEmptyBank() {
        quizBinding.questionField.visibility = View.INVISIBLE
        quizBinding.spinner.visibility = View.INVISIBLE
        quizBinding.selectOption.visibility = View.INVISIBLE
        quizBinding.nextQuestion.visibility = View.INVISIBLE

        quizBinding.scoreTextView.text = "This Quiz is empty"
    }

    /**
     * Function updates list of options, label with question text and all components responsible
     * for showing users new question.
     */
    @SuppressLint("SetTextI18n")
    private fun askNextQuestion() {
        val listOfAnswers = mutableListOf<String>()
        listOfAnswers.add("No option selected")
        listOfAnswers.addAll(
            getAllAnswers(
                quizQuestions[questionNumber].firstOption,
                quizQuestions[questionNumber].secondOption,
                quizQuestions[questionNumber].thirdOption,
                quizQuestions[questionNumber].fourthOption,
                quizQuestions[questionNumber].fifthOption,
                quizQuestions[questionNumber].sixthOption,
                quizQuestions[questionNumber].seventhOption,
                quizQuestions[questionNumber].eightOption,
                quizQuestions[questionNumber].ninthOption,
                quizQuestions[questionNumber].tenthOption
            )
        )
        quizBinding.spinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item_1, listOfAnswers
        )
        quizBinding.questionField.text = quizQuestions[questionNumber].question
        questionNumber++
        quizBinding.scoreTextView.text = "Progress: $questionNumber/$numberOfQuestions"
    }

    /**
     * Function takes user to screen with quiz result.
     */
    private fun finishQuiz() {
        val transition =
            QuizDirections.actionQuizToResult(numberOfCorrectAnswers, numberOfQuestions, args.bankName)
        findNavController().navigate(transition)
    }

    /**
     * Function verifies answer given by user with answer stored in the database.
     */
    private fun verifyAnswer() {
        if (quizBinding.spinner.selectedItem.toString() ==
            quizQuestions[questionNumber - 1].rightOption
        ) {
            numberOfCorrectAnswers++
        }
    }

    /**
     * Function is responsible for storing all answer in the list.
     * @return list full of question's options
     */
    private fun getAllAnswers(
        firstOption: String, secondOption: String, thirdOption: String,
        fourthOption: String, fifthOption: String, sixthOption: String,
        seventhOption: String, eighthOption: String, ninthOption: String,
        tenthOption: String
    ): List<String> {
        var listOfAnswers = mutableListOf<String>()
        listOfAnswers.add(firstOption)
        if (secondOption != "") listOfAnswers.add(secondOption) else return listOfAnswers
        if (thirdOption != "") listOfAnswers.add(thirdOption) else return listOfAnswers
        if (fourthOption != "") listOfAnswers.add(fourthOption) else return listOfAnswers
        if (fifthOption != "") listOfAnswers.add(fifthOption) else return listOfAnswers
        if (sixthOption != "") listOfAnswers.add(sixthOption) else return listOfAnswers
        if (seventhOption != "") listOfAnswers.add(seventhOption) else return listOfAnswers
        if (eighthOption != "") listOfAnswers.add(eighthOption) else return listOfAnswers
        if (ninthOption != "") listOfAnswers.add(ninthOption) else return listOfAnswers
        if (tenthOption != "") listOfAnswers.add(tenthOption) else return listOfAnswers

        return listOfAnswers
    }

    /**
     * Function is responsible for setting up first question in the quiz.
     */
    @SuppressLint("SetTextI18n")
    private fun setUpFirstQuestion() {
        numberOfQuestions = quizQuestions.size
        quizBinding.scoreTextView.text = "Progress: $questionNumber/$numberOfQuestions"
        quizBinding.questionField.text = quizQuestions[0].question

        listOfAnswers.add("No option selected")

        listOfAnswers.addAll(
            getAllAnswers(
                quizQuestions[0].firstOption,
                quizQuestions[0].secondOption,
                quizQuestions[0].thirdOption,
                quizQuestions[0].fourthOption,
                quizQuestions[0].fifthOption,
                quizQuestions[0].sixthOption,
                quizQuestions[0].seventhOption,
                quizQuestions[0].eightOption,
                quizQuestions[0].ninthOption,
                quizQuestions[0].tenthOption
            )
        )

        quizBinding.spinner.adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_list_item_1, listOfAnswers
        )
    }
}