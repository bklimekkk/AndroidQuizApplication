/**
 * Represents screen which enables users to view result of the quiz they taken.
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidquizapplication.databinding.FragmentResultBinding

class Result : Fragment() {
    private lateinit var resultBinding: FragmentResultBinding
    private val args by navArgs<ResultArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resultBinding = FragmentResultBinding.inflate(inflater, container, false)

        val correctAnswers = args.numberOfCorrectAnswers
        val questionsNumber = args.numberOfQuestions

        resultBinding.resultLabel.text = "$correctAnswers/$questionsNumber"

        resultBinding.doneButton.setOnClickListener {
            val transition = ResultDirections.actionResultToQuizzes()
            findNavController().navigate(transition)
        }

        resultBinding.tryAgain.setOnClickListener {
            val transition = ResultDirections.actionResultToQuiz(args.bankName)
            findNavController().navigate(transition)
        }


        return resultBinding.root
    }

}