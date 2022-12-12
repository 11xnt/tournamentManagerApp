package com.example.tournamentmanagerapp.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.example.tournamentmanagerapp.helpers.*
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "tournaments.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<TournamentModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class TournamentJSONStore(private val context: Context) : TournamentStore {

    var tournaments = mutableListOf<TournamentModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<TournamentModel> {
        logAll()
        return tournaments
    }

    override fun findOne(id: Long): TournamentModel? {
        TODO("Not yet implemented")
    }

    override fun create(tournament: TournamentModel) {
        tournament.id = generateRandomId()
        tournaments.add(tournament)
        serialize()
    }


    override fun update(tournament: TournamentModel) {
        // todo
    }

    override fun delete(tournament: TournamentModel) {
        TODO("Not yet implemented")
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(tournaments, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        tournaments = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        tournaments.forEach { Timber.i("$it") }
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