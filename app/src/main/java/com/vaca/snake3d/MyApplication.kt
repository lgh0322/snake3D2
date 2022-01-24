package com.vaca.snake3d

import android.app.Application
import android.content.res.Resources

class MyApplication : Application() {
    companion object {
        lateinit var myresources: Resources
    }

    override fun onCreate() {
        super.onCreate()
        myresources = resources
    }
}