package ru.mrrobot1413.testTask.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.mrrobot1413.testTask.ui.MainActivity
import ru.mrrobot1413.testTask.R
import ru.mrrobot1413.testTask.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    private val binding: ActivitySplashBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        supportActionBar?.hide()
        lifecycleScope.launch {
            delay(2500L)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}