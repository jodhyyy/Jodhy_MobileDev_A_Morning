package com.example.jodhymobiledevmorning

import android.app.AlarmManager
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.jodhymobiledevmorning.databinding.ActivityDashboardBinding

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.game.setOnClickListener {
            startActivity(Intent(this, Game::class.java))
        }

        tv = findViewById(R.id.recivUsername)
        val username = intent.getParcelableExtra<User>("User")?.username
        tv.text = "$username"

        replaceFragment(Home())
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpTabBar()
    }

    private fun setUpTabBar() {
        binding.bottomNavBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> replaceFragment(Home())
                R.id.nav_alarm -> replaceFragment(AlarmManager())
                R.id.nav_retrofit -> replaceFragment(Retrofit())
                else -> {
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .addToBackStack(null)
            .commit()
    }
}
