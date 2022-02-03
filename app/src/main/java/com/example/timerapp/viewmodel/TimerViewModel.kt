package com.example.timerapp.viewmodel

import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.timerapp.model.TimeInfo
import com.example.timerapp.repo.ClientsRepo
import java.util.*

class TimerViewModel: ViewModel() {

    private val repo = ClientsRepo()
    private var timeInfo = MutableLiveData<TimeInfo>()
    private val timeElapsed = MutableLiveData<Long>()
    private lateinit var timer:Timer

    fun getElapsedTime(): LiveData<Long> = timeElapsed
    fun getTimeInfo(): LiveData<TimeInfo> = timeInfo


    init {


        if (repo.isRunning()) {
            timeInfo.postValue(TimeInfo(true, repo.getLastTimerValue()))
            runTimer()
        }
        else {
            timeInfo.postValue(TimeInfo(false, repo.getLastTimerValue()))
        }

    }

    fun initTimer() {
        timeInfo.value?.let { _timeInfo ->
            if (_timeInfo.isRunning) {
                stopTimer()
            } else {
                runTimer()
            }
        }
    }

    private fun stopTimer() {
        timer.cancel()
        timeInfo.postValue(TimeInfo(false, 0L))
        repo.isRunning(false)
        timeElapsed.postValue(0L)
        repo.saveTimer(0L)
    }

    private fun runTimer() {
        timer = Timer()
        val initialTime = SystemClock.elapsedRealtime() - repo.getLastTimerValue()// capture time which starts with zero minus previous recorded time
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                timeElapsed.postValue(SystemClock.elapsedRealtime() - initialTime)
            }

        }, 1000L, 1000L)
        timeInfo.postValue(TimeInfo(true))
        repo.isRunning(true)
    }

    fun onActivityExits() {
        timeElapsed.value?.let {
            repo.saveTimer(it)
        }

    }

    fun getLastTimerValue() = repo.getLastTimerValue()

}