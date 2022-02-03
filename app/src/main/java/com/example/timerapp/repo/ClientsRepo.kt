package com.example.timerapp.repo

import com.example.timerapp.data.SharedPrefManager

class ClientsRepo {


    private var sharedPrefManager = SharedPrefManager.instance

    fun getLastTimerValue() = sharedPrefManager.getLastTimerValue()
    fun isRunning() = sharedPrefManager.isRunning()

    fun saveTimer(value:Long) {
        sharedPrefManager.saveTimer(value)
    }
    fun isRunning(value:Boolean) {
        sharedPrefManager.isRunning(value)
    }

}