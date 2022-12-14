package com.example.tournamentmanagerapp.models.team

import android.content.Context
import android.net.Uri
import com.google.gson.reflect.TypeToken
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.reflect.Type
import java.util.*
import kotlin.collections.ArrayList

val listType: Type = object : TypeToken<ArrayList<TeamModel>>() {}.type

val db = Firebase.firestore
var teamModels = ArrayList<TeamModel>()
val teams = db.collection("teams")
fun generateRandomId(): Long {
    return Random().nextLong()
}

class TeamJSONStore(private val context: Context) : TeamStore {

    override fun findAll(): ArrayList<TeamModel> {
        teams
            .get()
            .addOnSuccessListener { result ->
                teamModels = ArrayList()
                for (document in result) {
                    var team = TeamModel()
                    team.id = document.get("id") as Long
                    team.name = document.get("name") as String
                    team.image = Uri.parse(document.get("image") as String)
                    println("here"+document)
                    teamModels.add(team)
                }
            }
        return teamModels
    }

    override fun findOne(name: String): TeamModel? {
        var team = TeamModel()
        teams.document(name)
            .get()
            .addOnSuccessListener { result ->
                team.id = result.get("id") as Long
                team.name = result.get("name") as String
                team.image = Uri.parse((result.get("image") as String)) as Uri
            }
        return team
    }

    override fun create(team: TeamModel) {
        team.id = generateRandomId()
        teams.document(team.name).set(team)
    }


    override fun update(team: TeamModel) {
        teams.document(team.name).set(team)
    }

    override fun delete(team: TeamModel) {
        teams.document(team.name).delete()
    }

}
