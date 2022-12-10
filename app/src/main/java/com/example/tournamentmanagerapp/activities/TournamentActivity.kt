package com.example.tournamentmanagerapp.activities

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract.CalendarAlerts
import com.example.tournamentmanagerapp.databinding.ActivityTournamentBinding
import com.example.tournamentmanagerapp.models.TournamentModel
import com.github.ajalt.timberkt.Timber
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber.i

//import android.app.
class TournamentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTournamentBinding
    var tournament = TournamentModel()
    var tournaments = ArrayList<TournamentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())

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
                tournaments.add(tournament.copy())
            } else {
                Snackbar
                    .make(it, "Please fill in all fields", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }
}