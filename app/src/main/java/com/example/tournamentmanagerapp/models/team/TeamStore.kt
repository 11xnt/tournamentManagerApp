package com.example.tournamentmanagerapp.models.team

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot

interface TeamStore {
    fun findAll(): ArrayList<TeamModel>
    fun findOne(name: String): TeamModel?
    fun create(team: TeamModel)
    fun update(team: TeamModel)
    fun delete(team: TeamModel)
}