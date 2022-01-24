/**
 * Represents screen which enables users to edit (change text) or delete question.
 * @author Bartosz Klimek
 * @version 1
 */
package com.example.androidquizapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidquizapplication.databinding.FragmentEditOrDeleteBinding
import com.example.androidquizapplication.models.QuizViewModel

class EditOrDelete : Fragment() {

    private val args by navArgs<EditOrDeleteArgs>()
    private val viewModel: QuizViewModel by viewModels()

    private lateinit var editOrDeleteBinding: FragmentEditOrDeleteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        editOrDeleteBinding = FragmentEditOrDeleteBinding.inflate(
            inflater, container,
            false
        )

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Edit or delete question"

        editOrDeleteBinding.questionEditText.setText(args.question)

        //If users changed question, after clicking this button, new version of the question will
        //be saved.
        editOrDeleteBinding.saveButton.setOnClickListener {
            viewModel.updateQuestion(
                args.question, args.bankName,
                editOrDeleteBinding.questionEditText.text.toString()
            )

            val transition = EditOrDeleteDirections.actionEditOrDeleteToQuestions(args.bankName)
            findNavController().navigate(transition)
        }

        //Thanks to this line, upper action bar will be able to show menu in the top-right corner.
        setHasOptionsMenu(true)
        return editOrDeleteBinding.root
    }

    /**
     * Function finds xml file used to delete question.
     * [menu] is menu containing delete option.
     * [inflater] links menu from xml file with current screen (action bar).
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_question, menu)
    }

    /**
     * Function specifies what to do after delete item is selected.
     * @return function it overrides.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_question) {
            deleteQuestion()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Function responsible for deleting current question. It also presents users with pop-up
     * window asking them if they are sure that they want to delete this question. Clicking 'yes'
     * deletes question, clicking 'no' does nothing.
     */
    private fun deleteQuestion() {
        val message = AlertDialog.Builder(requireContext())
        val question = args.question
        message.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteQuestion(args.question, args.bankName)
            val transition = EditOrDeleteDirections.actionEditOrDeleteToQuestions(args.bankName)
            findNavController().navigate(transition)
        }

        message.setNegativeButton("No") { _, _ -> }

        message.setTitle("Delete question")
        message.setMessage("Are sure that you want to delete: '$question'?")
        message.create().show()
    }
}