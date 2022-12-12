package com.example.tournamentmanagerapp.models.tournament

interface TournamentStore {
    fun findAll(): List<TournamentModel>
    fun findOne(id: Long): TournamentModel?
    fun create(tournament: TournamentModel)
    fun update(tournament: TournamentModel)
    fun delete(tournament: TournamentModel)
}