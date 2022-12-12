package com.example.tournamentmanagerapp.models

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class TournamentMemStore : TournamentStore {

    val tournaments = ArrayList<TournamentModel>()

    override fun findAll(): List<TournamentModel> {
        return tournaments
    }

    override fun findOne(id: Long) : TournamentModel? {
        var foundTournament: TournamentModel? = tournaments.find { p -> p.id == id }
        return foundTournament
    }

    override fun create(tournament: TournamentModel) {
        tournament.id = getId()
        tournaments.add(tournament)
    }

    override fun update(tournament: TournamentModel) {
        var foundTournament: TournamentModel? = tournaments.find { p -> p.id == tournament.id}
        if (foundTournament != null) {
            foundTournament.title = tournament.title
            foundTournament.org = tournament.org
            foundTournament.startDate = tournament.startDate
            foundTournament.maxTeams = tournament.maxTeams
            foundTournament.image = tournament.image
        }
    }

    override fun delete(tournament: TournamentModel) {
        var foundTournament = findOne(tournament.id!!)
        if (foundTournament != null) {
            tournaments.remove(tournament)
        }
    }

}