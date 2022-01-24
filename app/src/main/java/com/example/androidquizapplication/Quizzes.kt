/**
 * Represents screen which enables users to view all quizzes.
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidquizapplication.adapters.QuizzesAdapter
import com.example.androidquizapplication.databinding.FragmentQuizzesBinding
import com.example.androidquizapplication.models.QuizViewModel

class Quizzes : Fragment() {
    private val viewModel: QuizViewModel by viewModels()
    private lateinit var quizzesBinding: FragmentQuizzesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        quizzesBinding = FragmentQuizzesBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Quizzes"

        quizzesBinding.quizzesBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_quizzes_to_startScreen)
        }

        setUpQuizzesTable()

        return quizzesBinding.root
    }

    /**
     * Function is responsible for displaying table of quizzes by populating recycler view with
     * content from banks table in database and adding observer.
     */
    private fun setUpQuizzesTable() {
        val quizzesAdapter = QuizzesAdapter()
        val quizzesRecyclerView = quizzesBinding.quizzesRecyclerView
        quizzesRecyclerView.adapter = quizzesAdapter
        quizzesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllBanks.observe(viewLifecycleOwner, Observer { bank ->
            quizzesAdapter.setBanks(bank)
        })
    }
}