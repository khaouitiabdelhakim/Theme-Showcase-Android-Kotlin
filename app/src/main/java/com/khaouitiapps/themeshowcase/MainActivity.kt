package com.khaouitiapps.themeshowcase


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.khaouitiapps.themeshowcase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        // Set up theme toggle button
        binding.btnToggleTheme.setOnClickListener {
            toggleTheme()
        }

        // Set button text based on current theme
        updateButtonText()
    }

    private fun toggleTheme() {
        val currentNightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK

        if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        // Explicitly recreate the activity to apply theme changes
        recreate()
    }

    private fun updateButtonText() {
        val currentNightMode = resources.configuration.uiMode and android.content.res.Configuration.UI_MODE_NIGHT_MASK

        if (currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
            binding.btnToggleTheme.text = getString(R.string.switch_to_light)
        } else {
            binding.btnToggleTheme.text = getString(R.string.switch_to_dark)
        }
    }
}