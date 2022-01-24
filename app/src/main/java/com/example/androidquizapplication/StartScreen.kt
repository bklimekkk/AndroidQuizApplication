/**
 * Represents screen which enables users to choose from two application modes: teacher and
 * student (quiz mode).
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
import androidx.navigation.fragment.findNavController
import com.example.androidquizapplication.databinding.FragmentStartScreenBinding


class StartScreen : Fragment() {
    private lateinit var startScreenFragment: FragmentStartScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startScreenFragment = FragmentStartScreenBinding.inflate(
            inflater, container,
            false
        )

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Modes"

        startScreenFragment.teacherButton.setOnClickListener {
            findNavController().navigate(R.id.action_startScreen_to_teacherBanks)
        }

        startScreenFragment.studentButton.setOnClickListener {
            findNavController().navigate(R.id.action_startScreen_to_quizzes)
        }

        return startScreenFragment.root
    }

}