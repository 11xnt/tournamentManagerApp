package com.example.tournamentmanagerapp.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tournamentmanagerapp.R
import com.example.tournamentmanagerapp.databinding.ActivityTournamentBinding
import com.example.tournamentmanagerapp.main.MainApp
import com.example.tournamentmanagerapp.models.TournamentModel
import com.github.ajalt.timberkt.Timber
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber.i

//import android.app.
class TournamentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTournamentBinding
    var tournament = TournamentModel()
    lateinit var app : MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarCancel.title = title
        setSupportActionBar(binding.toolbarCancel)

        Timber.plant(Timber.DebugTree())

        app = application as MainApp
        i("Tournament Activity started...")

        // Date picker sourced from: https://www.geeksforgeeks.org/datepicker-in-android/
        binding.tournamentStartDate.setOnClickListener {
            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this,
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our text view.
                    binding.tournamentStartDate.hint =
                        (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }

        binding.btnAdd.setOnClickListener() {
            tournament.title = binding.tournamentTitle.text.toString()
            tournament.org = binding.tournamentOrg.text.toString()
            tournament.startDate = binding.tournamentStartDate.hint.toString()
            tournament.maxTeams = Integer.parseInt(binding.tournamentMaxTeams.text.toString())
            if (tournament.title.isNotEmpty() && tournament.org.isNotEmpty() && tournament.startDate.isNotEmpty()
                && tournament.maxTeams > 0) {
                i("add Button Pressed: $tournament")
                app.tournaments.add(tournament.copy())
                for (i in app.tournaments.indices) {
                    i("Tournament[$i]:${this.app.tournaments[i]}")
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar
                    .make(it, "Please fill in all fields", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_cancel, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // if cancel button is pressed, finish action and bring back to tournament list
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}