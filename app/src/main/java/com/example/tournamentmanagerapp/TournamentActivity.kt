package com.example.tournamentmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tournamentmanagerapp.databinding.ActivityTournamentBinding
import timber.log.Timber
import timber.log.Timber.i

//import android.app.
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTournamentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTournamentBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Timber.plant(Timber.DebugTree())

        i("Placemark Activity started...")

        binding.greetingButton.setOnClickListener {
            val greetingText = getString(R.string.greeting_text)
            Toast.makeText(applicationContext, greetingText, Toast.LENGTH_LONG).show()
        }
    }
}