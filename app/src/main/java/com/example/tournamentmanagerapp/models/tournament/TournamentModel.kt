package com.example.tournamentmanagerapp.models.tournament

import android.net.Uri
import android.os.Parcelable
import com.example.tournamentmanagerapp.models.team.TeamModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class TournamentModel(var id: Long = 0,
                           var title: String = "",
                           var org: String = "",
                           var startDate: String = "",
                           var maxTeams: Int = 0,
                           var image: Uri = Uri.EMPTY,
                           var partTeams: ArrayList<TeamModel> = arrayListOf()
) : Parcelable
