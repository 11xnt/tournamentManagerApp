package com.example.tournamentmanagerapp.models.team

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.example.tournamentmanagerapp.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "teams.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<TeamModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class TeamJSONStore(private val context: Context) : TeamStore {

    var teams = mutableListOf<TeamModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<TeamModel> {
        logAll()
        return teams
    }

    override fun findOne(name: String): TeamModel? {
        val teamsList = findAll() as ArrayList<TeamModel>
        val foundTeam: TeamModel? = teamsList.find { p -> p.name == name }
        return if (foundTeam != null) {
            serialize()
            foundTeam
        } else {
            TeamModel()
        }
    }

    override fun create(team: TeamModel) {
        team.id = generateRandomId()
        teams.add(team)
        serialize()
    }


    override fun update(team: TeamModel) {
        val teamsList = findAll() as ArrayList<TeamModel>
        var foundTeam: TeamModel? = teamsList.find { p -> p.id == team.id }
        if (foundTeam != null) {
            foundTeam.name = team.name
            foundTeam.image = team.image
        }
        serialize()
    }

    override fun delete(team: TeamModel) {
        teams.remove(team)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(teams, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        teams = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        teams.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}