package com.example.tournamentmanagerapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.ActionMenuView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tournamentmanagerapp.R
import com.example.tournamentmanagerapp.adapters.TournamentAdapter
import com.example.tournamentmanagerapp.adapters.TournamentListener
import com.example.tournamentmanagerapp.databinding.ActivityTournamentListBinding
import com.example.tournamentmanagerapp.main.MainApp
import com.example.tournamentmanagerapp.models.tournament.TournamentModel


class TournamentListActivity : AppCompatActivity(), TournamentListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityTournamentListBinding
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTournamentListBinding.inflate(layoutInflater)
        binding.toolbar.title = title

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = TournamentAdapter(app.tournaments.findAll(),this)

    }

    // Sourced from:
    // https://stackoverflow.com/questions/32808996/android-add-two-toolbars-in-the-same-activity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val bottomBar: ActionMenuView = findViewById<View>(R.id.toolbarChange) as ActionMenuView
        val bottomMenu: Menu = bottomBar.getMenu()
        menuInflater.inflate(R.menu.menu_change, bottomMenu)

        for (i in 0 until bottomMenu.size()) {
            bottomMenu.getItem(i).setOnMenuItemClickListener { item -> onOptionsItemSelected(item) }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, TournamentActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.change_team -> {
                val launcherIntent = Intent(this, TeamListActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.change_tour -> {
            }
            R.id.theme -> {
                if(isDarkThemeOn()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun Context.isDarkThemeOn(): Boolean {
        return resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
    }

    override fun onTournamentClick(tournament: TournamentModel, pos: Int) {
        val launcherIntent = Intent(this, TournamentActivity::class.java)
        launcherIntent.putExtra("tournament_edit", tournament)
        position = pos
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.tournaments.findAll().size)
            }
            else // Deleting
                if (it.resultCode == 99)     (binding.recyclerView.adapter)?.notifyItemRemoved(position)
        }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.tournaments.findAll().size)
            }
        }
}
