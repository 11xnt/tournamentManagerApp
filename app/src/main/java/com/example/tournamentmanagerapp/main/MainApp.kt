package com.example.tournamentmanagerapp.main

import android.app.Application
import com.example.tournamentmanagerapp.models.TournamentMemStore
import com.example.tournamentmanagerapp.models.TournamentJSONStore
import com.example.tournamentmanagerapp.models.TournamentModel
import com.example.tournamentmanagerapp.models.TournamentStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var tournaments: TournamentStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        tournaments = TournamentJSONStore(applicationContext)
        i("Tournament Manager started")
//        tournaments.add(TournamentModel(0,"Tour1","Org1","22-12-2022",20))
//        tournaments.add(TournamentModel(1,"Tour2","Org2","22-12-2023",10))
//        tournaments.add(TournamentModel(2,"Tour3","Org3","22-12-2024",64))
    }
}