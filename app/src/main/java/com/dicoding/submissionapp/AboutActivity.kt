package com.dicoding.submissionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val aboutImageView: ImageView = findViewById(R.id.aboutImageView)
        val aboutNameTextView: TextView = findViewById(R.id.aboutNameTextView)
        val aboutEmailTextView: TextView = findViewById(R.id.aboutEmailTextView)

        aboutImageView.setImageResource(R.drawable.my_photo)
        aboutNameTextView.text = (getString(R.string.my_name))
        aboutEmailTextView.text = (getString(R.string.my_email))
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}