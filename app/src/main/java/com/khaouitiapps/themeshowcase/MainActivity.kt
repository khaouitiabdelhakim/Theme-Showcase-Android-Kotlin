package com.khaouitiapps.themeshowcase

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.khaouitiapps.themeshowcase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val PREF_NAME = "theme_prefs"
        private const val KEY_THEME_MODE = "theme_mode"

        // Theme mode constants
        const val MODE_LIGHT = 0
        const val MODE_DARK = 1
        const val MODE_SYSTEM = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        // Apply saved theme before setting content view
        applyTheme(getSavedThemeMode())

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupThemeSpinner()
    }

    private fun setupThemeSpinner() {
        // Create theme options array
        val themeOptions = arrayOf(
            getString(R.string.light_theme),
            getString(R.string.dark_theme),
            getString(R.string.system_theme)
        )

        // Create and set adapter
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            themeOptions
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTheme.adapter = adapter

        // Set initial selection based on saved preference
        binding.spinnerTheme.setSelection(getSavedThemeMode())

        // Set up listener for theme changes
        binding.spinnerTheme.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position != getSavedThemeMode()) {
                    saveAndApplyTheme(position)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
    }

    private fun getSavedThemeMode(): Int {
        // Default to system theme if no preference is saved
        return sharedPreferences.getInt(KEY_THEME_MODE, MODE_SYSTEM)
    }

    private fun saveAndApplyTheme(themeMode: Int) {
        // Save theme preference
        sharedPreferences.edit().putInt(KEY_THEME_MODE, themeMode).apply()

        // Apply the selected theme
        applyTheme(themeMode)
    }

    private fun applyTheme(themeMode: Int) {
        when (themeMode) {
            MODE_LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            MODE_DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            MODE_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}