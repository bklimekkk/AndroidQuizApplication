package com.example.androidquizapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.androidquizapplication.R
import com.example.androidquizapplication.TeacherBanksDirections
import com.example.androidquizapplication.entities.Bank
import kotlinx.android.synthetic.main.bank.view.*


class BanksAdapter: RecyclerView.Adapter<BanksAdapter.BanksViewHolder>() {

    private var banksList = emptyList<Bank>()
    class BanksViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksViewHolder {
        return BanksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bank, parent, false))
    }

    override fun onBindViewHolder(holder: BanksViewHolder, position: Int) {
        val currentBank = banksList[position]
        holder.itemView.nameTextView.text = currentBank.name

        holder.itemView.card.setOnClickListener {
            var transition = TeacherBanksDirections.actionTeacherBanksToQuestions(currentBank.name)
            holder.itemView.findNavController().navigate(transition)
        }
    }

    override fun getItemCount(): Int {
        return banksList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setBanks(banks: List<Bank>) {
        this.banksList = banks
        notifyDataSetChanged()
    }
}