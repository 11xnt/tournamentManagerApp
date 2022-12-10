package com.example.tournamentmanagerapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tournamentmanagerapp.databinding.CardTournamentBinding
import com.example.tournamentmanagerapp.models.TournamentModel

class TournamentAdapter constructor(private var tournaments: List<TournamentModel>) :

    RecyclerView.Adapter<TournamentAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardTournamentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val tournament = tournaments[holder.adapterPosition]
        holder.bind(tournament)
    }

    override fun getItemCount(): Int = tournaments.size

    class MainHolder(private val binding : CardTournamentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tournament: TournamentModel) {
            binding.tournamentTitle.text = tournament.title
            binding.tournamentOrg.text = "Organiser: ${tournament.org}"
            binding.tournamentStartDate.text = "Start Date: ${tournament.startDate}"
            binding.tournamentMaxTeams.text = "Max Teams: ${tournament.maxTeams}"
        }
    }
}