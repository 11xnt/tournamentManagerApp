package com.example.tournamentmanagerapp.main

import android.app.Application
import com.example.tournamentmanagerapp.models.team.TeamJSONStore
import com.example.tournamentmanagerapp.models.team.TeamStore
import com.example.tournamentmanagerapp.models.tournament.TournamentJSONStore
import com.example.tournamentmanagerapp.models.tournament.TournamentStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var tournaments: TournamentStore
    lateinit var teams: TeamStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        tournaments = TournamentJSONStore(applicationContext)
        teams = TeamJSONStore(applicationContext)
        i("Tournament Manager started")
    }
}