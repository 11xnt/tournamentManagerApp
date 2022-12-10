package com.example.tournamentmanagerapp.main

import android.app.Application
import com.example.tournamentmanagerapp.models.TournamentModel
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val tournaments = ArrayList<TournamentModel>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("Tournament Manager started")
//        tournaments.add(TournamentModel(0,"Tour1","Org1","22-12-2022",20))
//        tournaments.add(TournamentModel(1,"Tour2","Org2","22-12-2023",10))
//        tournaments.add(TournamentModel(2,"Tour3","Org3","22-12-2024",64))
    }
}