package com.example.androidquizapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidquizapplication.QuizzesDirections
import com.example.androidquizapplication.R
import com.example.androidquizapplication.TeacherBanksDirections
import com.example.androidquizapplication.entities.Bank
import kotlinx.android.synthetic.main.bank.view.*


class QuizzesAdapter: RecyclerView.Adapter<QuizzesAdapter.QuizzesViewHolder>() {

    private var quizzesList = emptyList<Bank>()
    class QuizzesViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizzesViewHolder{
        return QuizzesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bank, parent, false))
    }

    override fun onBindViewHolder(holder: QuizzesViewHolder, position: Int) {
        val currentBank = quizzesList[position]
        holder.itemView.nameTextView.text = currentBank.name

        holder.itemView.card.setOnClickListener {
            val transition = QuizzesDirections.actionQuizzesToQuiz(currentBank.name)
            holder.itemView.findNavController().navigate(transition)
        }
    }

    override fun getItemCount(): Int {
        return quizzesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBanks(banks: List<Bank>) {
        this.quizzesList = banks
        notifyDataSetChanged()
    }
}