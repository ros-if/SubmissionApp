package com.dicoding.submissionapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.submissionapp.R.*


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

        val btnShare = findViewById<Button>(id.action_share)

        btnShare.setOnClickListener {
            shareProduct()
        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("QueryPermissionsNeeded")
    private fun shareProduct() {

        val product = intent.getParcelableExtra<Product>("product")

        val shareIntent = Intent(Intent.ACTION_SEND)

        shareIntent.type = "text/plain"

        val shareText = "*PROMO SPESIAL* untuk anda !!! \n\n*${product?.name}* \n\n${product?.description}"

        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)

        if (shareIntent.resolveActivity(packageManager) != null) {
            startActivity(shareIntent)
        } else {
            @Suppress("UNREACHABLE_CODE")
            return error(message = "Error")
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