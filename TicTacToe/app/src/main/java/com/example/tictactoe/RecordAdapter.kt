package com.example.tictactoe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.record_item.view.*

class RecordAdapter : RecyclerView.Adapter<RecordViewHolder>() {

    private var mainList = ArrayList<Player>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.record_item, parent, false)
        return RecordViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.bind(mainList[position])
    }

    override fun getItemCount(): Int {
        return mainList.size
    }

    fun submitList(players: ArrayList<Player>){
        mainList = players
        notifyDataSetChanged()
    }

}

class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name = itemView.tvName
    private val wins = itemView.tvWins
    private val loses = itemView.tvLoses

    fun bind(player: Player) {
        name.text = player.name
        wins.text = "Wins: ${player.wins}"
        loses.text = "Loses ${player.loses}"
    }
}