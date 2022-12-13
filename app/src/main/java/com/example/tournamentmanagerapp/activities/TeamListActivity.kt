package com.example.tournamentmanagerapp.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.ActionMenuView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tournamentmanagerapp.R
import com.example.tournamentmanagerapp.databinding.ActivityTeamListBinding
import com.example.tournamentmanagerapp.main.MainApp
import com.example.tournamentmanagerapp.adapters.TeamAdapter
import com.example.tournamentmanagerapp.adapters.TeamListener
import com.example.tournamentmanagerapp.models.team.TeamModel

class TeamListActivity : AppCompatActivity(), TeamListener {

    lateinit var app: MainApp
    private lateinit var binding: ActivityTeamListBinding
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamListBinding.inflate(layoutInflater)
        binding.toolbar.title = title
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = TeamAdapter(app.teams.findAll(),this)
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
                val launcherIntent = Intent(this, TeamActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.change_tour -> {
                val launcherIntent = Intent(this, TournamentListActivity::class.java)
                getResult.launch(launcherIntent)
            }
            R.id.change_team -> {
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
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    override fun onTeamClick(team: TeamModel, pos: Int) {
        val launcherIntent = Intent(this, TeamActivity::class.java)
        launcherIntent.putExtra("team_edit", team)
        position = pos
        getClickResult.launch(launcherIntent)
    }

    private val getClickResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                (binding.recyclerView.adapter)?.
                notifyItemRangeChanged(0,app.teams.findAll().size)
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
                notifyItemRangeChanged(0,app.teams.findAll().size)
            }
        }
}