package com.dicoding.submissionapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashText = findViewById<TextView>(R.id.splashText)
        splashText.text = "SOERJA PAPERBAG"

        val splashScreenDuration = 2000L
        val mainActivityIntent = Intent(this, MainActivity::class.java)

        Thread {
            Thread.sleep(splashScreenDuration)
            startActivity(mainActivityIntent)
            finish()
        }.start()
    }
}