package com.example.tournamentmanagerapp.activities

import android.app.AlertDialog
import android.app.AlertDialog.*
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tournamentmanagerapp.R
import com.example.tournamentmanagerapp.databinding.ActivityTournamentBinding
import com.example.tournamentmanagerapp.helpers.showImagePicker
import com.example.tournamentmanagerapp.main.MainApp
import com.example.tournamentmanagerapp.models.team.TeamStore
import com.example.tournamentmanagerapp.models.tournament.TournamentModel
import com.github.ajalt.timberkt.Timber
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import timber.log.Timber.i

//import android.app.
class TournamentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTournamentBinding
    var tournament = TournamentModel()
    lateinit var app : MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    lateinit var teams: TeamStore

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

        binding.selectTeams.setOnClickListener() {
            val alertDialogBuilder = AlertDialog.Builder(this)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Select Teams")
            builder.setMessage("TODO")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }

            builder.setNeutralButton("Maybe") { dialog, which ->
                Toast.makeText(applicationContext,
                    "Maybe", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        if (intent.hasExtra("tournament_edit")) {
            binding.btnAdd.setText(R.string.button_updateTournament)
            binding.chooseImage.setText(R.string.button_updateImage)
            tournament = intent.extras?.getParcelable("tournament_edit")!!
            binding.tournamentTitle.setText(tournament.title)
            binding.tournamentOrg.setText(tournament.org)
            binding.tournamentStartDate.setHint(tournament.startDate)
            binding.tournamentMaxTeams.setText(tournament.maxTeams.toString())
            Picasso.get()
                .load(tournament.image)
                .into(binding.tournamentImage)
        }

        binding.btnAdd.setOnClickListener() {
            tournament.title = binding.tournamentTitle.text.toString()
            tournament.org = binding.tournamentOrg.text.toString()
            tournament.startDate = binding.tournamentStartDate.hint.toString()
            tournament.maxTeams = Integer.parseInt(binding.tournamentMaxTeams.text.toString())
            if (tournament.title.isNotEmpty() && tournament.org.isNotEmpty() && tournament.startDate.isNotEmpty()
                && tournament.maxTeams > 0) {

                if (intent.hasExtra("tournament_edit")) {
                    app.tournaments.update(tournament)
                } else {
                    app.tournaments.create(tournament.copy())
                }
                setResult(RESULT_OK)
                finish()
            } else {
                Snackbar
                    .make(it, getString(R.string.empty_fields), Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        binding.chooseImage.setOnClickListener {
            showImagePicker(imageIntentLauncher,this)
        }
        registerImagePickerCallback()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_cancel, menu)
        if (intent.hasExtra("tournament_edit")) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    // if cancel button is pressed, finish action and bring back to tournament list
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                setResult(99)
                app.tournaments.delete(tournament)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")

                            val image = result.data!!.data!!
                            contentResolver.takePersistableUriPermission(image,
                                Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            tournament.image = image

                            Picasso.get()
                                .load(tournament.image)
                                .into(binding.tournamentImage)
                            binding.chooseImage.setText(R.string.button_updateImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

}