package com.example.tournamentmanagerapp.models

interface TournamentStore {
    fun findAll(): List<TournamentModel>
    fun create(tournament: TournamentModel)
}