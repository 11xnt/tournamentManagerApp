package com.example.tournamentmanagerapp.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TournamentModel(var id: Long = 0,
                           var title: String = "",
                           var org: String = "",
                           var startDate: String = "",
                           var maxTeams: Int = 0,
                           var image: Uri = Uri.EMPTY
//                           var partTeams: ArrayList<TeamModel> = arrayListOf(),
//                           var winner: TeamModel = TeamModel()
) : Parcelable
