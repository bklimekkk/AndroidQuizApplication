/**
 * Represents screen which enables users to view all bank's questions.
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidquizapplication.adapters.QuestionsAdapter
import com.example.androidquizapplication.databinding.FragmentQuestionsBinding
import com.example.androidquizapplication.models.QuizViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels


class Questions : Fragment() {
    private lateinit var questionsBinding: FragmentQuestionsBinding
    private val args by navArgs<QuestionsArgs>()
    private val viewModel: QuizViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        questionsBinding = FragmentQuestionsBinding.inflate(
            inflater, container,
            false
        )

        questionsBinding.questionsBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_questions_to_teacherBanks)
        }

        val bankName = args.bankName
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Questions for '$bankName' bank"

        setUpQuestionsTable()

        questionsBinding.addQuestion.setOnClickListener {
            val transition = QuestionsDirections.actionQuestionsToCreateQuestion(
                args.bankName
            )
            findNavController().navigate(transition)
        }

        setHasOptionsMenu(true)
        return questionsBinding.root
    }

    /**
     * Function is responsible for displaying table of questions by populating recycler view with
     * content from questions table in database and adding observer.
     */
    private fun setUpQuestionsTable() {
        val questionsAdapter = QuestionsAdapter()
        val questionsRecyclerView = questionsBinding.questionsRecyclerView
        questionsRecyclerView.adapter = questionsAdapter
        questionsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllQuestions(args.bankName)
            .observe(viewLifecycleOwner, Observer { question ->
                questionsAdapter.setQuestions(question)
            })
    }

    /**
     * Function finds xml file used to delete bank.
     * [menu] is menu containing delete option.
     * [inflater] links menu from xml file with current screen (action bar).
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_bank, menu)
    }

    /**
     * Function specifies what to do after delete item is selected
     * [item] is current menu item.
     * @return function it overrides.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_bank) {
            deleteBank()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Function is responsible for deleting current bank. It also presents users with pop-up
     * window asking them if they are sure that they want to delete this bank. Clicking 'yes'
     * deletes it, clicking 'no' does nothing.
     */
    private fun deleteBank() {
        val message = AlertDialog.Builder(requireContext())
        val bankName = args.bankName
        message.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteBank(bankName)
            findNavController().navigate(R.id.action_questions_to_teacherBanks)
        }

        message.setNegativeButton("No") { _, _ -> }

        message.setTitle("Delete bank")
        message.setMessage("Are sure that you want to delete bank: '$bankName'?")
        message.create().show()

    }
}