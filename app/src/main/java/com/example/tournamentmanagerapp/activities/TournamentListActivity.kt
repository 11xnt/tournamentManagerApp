package com.example.tournamentmanagerapp.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tournamentmanagerapp.R
import com.example.tournamentmanagerapp.databinding.ActivityTournamentListBinding
import com.example.tournamentmanagerapp.databinding.CardTournamentBinding
import com.example.tournamentmanagerapp.main.MainApp
import com.example.tournamentmanagerapp.models.TournamentModel

class TournamentListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    private lateinit var binding: ActivityTournamentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTournamentListBinding.inflate(layoutInflater)
        binding.toolbar.title = title
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = TournamentAdapter(app.tournaments)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, TournamentActivity::class.java)
                getResult.launch(launcherIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.tournaments.size)
            }
        }
}

class TournamentAdapter constructor(private var tournaments: List<TournamentModel>) :

    RecyclerView.Adapter<TournamentAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardTournamentBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val tournament = tournaments[holder.adapterPosition]
        holder.bind(tournament)
    }

    override fun getItemCount(): Int = tournaments.size

    class MainHolder(private val binding : CardTournamentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tournament: TournamentModel) {
            binding.tournamentTitle.text = tournament.title
            binding.tournamentOrg.text = "Organiser: ${tournament.org}"
            binding.tournamentStartDate.text = "Start Date: ${tournament.startDate}"
            binding.tournamentMaxTeams.text = "Max Teams: ${tournament.maxTeams}"
        }
    }
}