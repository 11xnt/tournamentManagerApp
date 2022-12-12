package com.example.tournamentmanagerapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.tournamentmanagerapp.R
import com.example.tournamentmanagerapp.databinding.ActivityTeamBinding
import com.example.tournamentmanagerapp.helpers.showImagePicker
import com.example.tournamentmanagerapp.main.MainApp
import com.example.tournamentmanagerapp.models.team.TeamModel
import com.github.ajalt.timberkt.Timber
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import timber.log.Timber.i

//import android.app.
class TeamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamBinding
    var team = TeamModel()
    lateinit var app : MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edit = true
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarCancel.title = title
        setSupportActionBar(binding.toolbarCancel)

        Timber.plant(Timber.DebugTree())

        app = application as MainApp
        i("Team Activity started...")


        if (intent.hasExtra("team_edit")) {
            binding.btnAdd.setText(R.string.button_updateTeam)
            binding.chooseImage.setText(R.string.button_updateImage)
            team = intent.extras?.getParcelable("team_edit")!!
            binding.teamName.setText(team.name)
            Picasso.get()
                .load(team.image)
                .into(binding.teamImage)
        }

        binding.btnAdd.setOnClickListener() {
            team.name = binding.teamName.text.toString()
            if (team.name.isNotEmpty()) {
                if (intent.hasExtra("team_edit")) {
                    app.teams.update(team)
                } else {
                    app.teams.create(team.copy())
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
        if (edit) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    // if cancel button is pressed, finish action and bring back to team list
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                setResult(99)
                app.teams.delete(team)
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
                            team.image = image

                            Picasso.get()
                                .load(team.image)
                                .into(binding.teamImage)
                            binding.chooseImage.setText(R.string.button_updateImage)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }

}