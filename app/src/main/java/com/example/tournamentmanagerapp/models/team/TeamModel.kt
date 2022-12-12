package com.example.tournamentmanagerapp.models.team

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamModel(var id: Long = 0,
                           var name: String = "",
                           var image: Uri = Uri.EMPTY
) : Parcelable
