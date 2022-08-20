package com.princeoprince.simpletodo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.princeoprince.simpletodo.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        makeFullScreen()

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        delayActivity()
    }

    private fun makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        hideStatusBar()
        supportActionBar?.hide()

    }

    private fun hideStatusBar() {
        WindowCompat.getInsetsController(window, window.decorView)
            ?.hide(WindowInsetsCompat.Type.statusBars() or
                    WindowInsetsCompat.Type.navigationBars())
    }

    private fun delayActivity() {
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish() }
        handler.postDelayed(runnable,2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}