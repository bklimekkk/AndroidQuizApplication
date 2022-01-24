/**
 * Represents screen which enables users to view all existing banks.
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidquizapplication.adapters.BanksAdapter
import com.example.androidquizapplication.databinding.FragmentTeacherBanksBinding
import com.example.androidquizapplication.models.QuizViewModel

class TeacherBanks : Fragment() {
    private lateinit var viewModel: QuizViewModel
    private lateinit var teacherBanksBinding: FragmentTeacherBanksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        teacherBanksBinding = FragmentTeacherBanksBinding.inflate(
            inflater, container,
            false
        )

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Question banks"

        teacherBanksBinding.teacherBanksBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_teacherBanks_to_startScreen)
        }

        setUpBanksTable()

        teacherBanksBinding.addBank.setOnClickListener {
            findNavController().navigate(R.id.action_teacherBanks_to_teacherNewBank)
        }
        return teacherBanksBinding.root
    }

    /**
     * Function is responsible for displaying table of question banks by populating recycler view
     * with content from banks table in database and adding observer.
     */
    private fun setUpBanksTable() {
        val banksAdapter = BanksAdapter()
        val banksRecyclerView = teacherBanksBinding.banksRecyclerView
        banksRecyclerView.adapter = banksAdapter
        banksRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        viewModel.getAllBanks.observe(viewLifecycleOwner, Observer { bank ->
            banksAdapter.setBanks(bank)
        })
    }
}