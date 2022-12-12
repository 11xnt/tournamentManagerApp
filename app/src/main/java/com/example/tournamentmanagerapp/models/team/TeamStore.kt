package com.example.tournamentmanagerapp.models.team

interface TeamStore {
    fun findAll(): List<TeamModel>
    fun findOne(id: Long): TeamModel?
    fun create(team: TeamModel)
    fun update(team: TeamModel)
    fun delete(team: TeamModel)
}