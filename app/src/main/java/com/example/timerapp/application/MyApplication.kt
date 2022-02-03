package com.example.timerapp.application

import android.app.Application
import com.example.timerapp.data.SharedPrefManager

public class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefManager.Companion.initializeInstance(this)
    }
}