package com.example.timerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.timerapp.viewmodel.TimerViewModel
import java.lang.String
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var viewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()

    }

    private fun setupUI() {
        findViewById<Button>(R.id.button).setOnClickListener(this)
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(TimerViewModel::class.java).apply {

            getElapsedTime().observe(this@MainActivity, {
                renderDisplayTime(it)
            })

            getTimeInfo().observe(this@MainActivity, { _timeInfo ->
                _timeInfo.time?.let { renderDisplayTime(it) }
                if (_timeInfo.isRunning) {
                    findViewById<Button>(R.id.button).text = "STOP"

                } else {
                    findViewById<Button>(R.id.button).text = "START"
                }
            })
        }
    }

    @SuppressLint("DefaultLocale")
    private fun renderDisplayTime(millis:Long) {
        val hms = String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
        )
        findViewById<TextView>(R.id.timer_tv).text = "$hms"
    }

    override fun onPause() {
        super.onPause()
        viewModel.onActivityExits()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.button -> {
                viewModel.initTimer()
            }
        }
    }


}