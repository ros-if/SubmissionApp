package com.dicoding.submissionapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.dicoding.submissionapp.R.*
import com.dicoding.submissionapp.R.id.*

class DetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val detailImageView: ImageView = findViewById(R.id.detailImageView)
        val detailTitleTextView: TextView = findViewById(R.id.detailTitleTextView)
        val detailDescriptionTextView: TextView = findViewById(R.id.detailDescriptionTextView)

        @Suppress("DEPRECATION") val product = intent.getParcelableExtra<Product>("product")

        product?.let {
            detailImageView.setImageResource(it.photo)
            detailTitleTextView.text = it.name
            detailDescriptionTextView.text = it.description
        }
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