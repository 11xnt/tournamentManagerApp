package com.example.tournamentmanagerapp.models

data class TournamentModel(var id: Long = 0,
                           var title: String = "",
                           var org: String = "",
                           var startDate: String = "",
                           var maxTeams: Int = 0,
//                           var partTeams: ArrayList<TeamModel> = arrayListOf(),
//                           var winner: TeamModel = TeamModel()
)
