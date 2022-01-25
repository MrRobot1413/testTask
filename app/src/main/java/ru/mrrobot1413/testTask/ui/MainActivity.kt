package ru.mrrobot1413.testTask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.mrrobot1413.testTask.R
import ru.mrrobot1413.testTask.databinding.ActivityMainBinding
import ru.mrrobot1413.testTask.ui.home.HomeFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.host, HomeFragment::class.java, null).commit()
    }
}