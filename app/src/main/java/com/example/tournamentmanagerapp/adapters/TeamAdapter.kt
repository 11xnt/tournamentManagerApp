package com.example.tournamentmanagerapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tournamentmanagerapp.databinding.CardTeamBinding
import com.example.tournamentmanagerapp.models.team.TeamModel
import com.squareup.picasso.Picasso

interface TeamListener {
    fun onTeamClick(team: TeamModel, pos: Int)
}

class TeamAdapter constructor(private var teams: List<TeamModel>,
                                    private val listener: TeamListener) :

    RecyclerView.Adapter<TeamAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardTeamBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, pos: Int) {
        val team = teams[holder.adapterPosition]
        holder.bind(team, listener)
    }

    override fun getItemCount(): Int = teams.size

    class MainHolder(private val binding : CardTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(team: TeamModel, listener: TeamListener) {
            binding.teamName.text = team.name
            Picasso.get().load(team.image).resize(200,200).into(binding.imageIcon)
            binding.root.setOnClickListener {
                listener.onTeamClick(team, adapterPosition)
            }
        }
    }
}