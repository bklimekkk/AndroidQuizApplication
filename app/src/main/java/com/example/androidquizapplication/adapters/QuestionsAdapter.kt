package com.example.androidquizapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidquizapplication.QuestionsDirections

import com.example.androidquizapplication.R

import com.example.androidquizapplication.entities.Question

import kotlinx.android.synthetic.main.bank.view.*


class QuestionsAdapter: RecyclerView.Adapter<QuestionsAdapter.QuestionsViewHolder>() {

    private var questionsList = emptyList<Question>()

    class QuestionsViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.question, parent, false))
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
        val currentQuestion = questionsList[position]
        holder.itemView.nameTextView.text = currentQuestion.question

        holder.itemView.card.setOnClickListener {
            var transition = QuestionsDirections.actionQuestionsToEditOrDelete(currentQuestion.question, currentQuestion.bankName)
            holder.itemView.findNavController().navigate(transition)
        }
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setQuestions(questions: List<Question>) {
        this.questionsList = questions
        notifyDataSetChanged()
    }
}