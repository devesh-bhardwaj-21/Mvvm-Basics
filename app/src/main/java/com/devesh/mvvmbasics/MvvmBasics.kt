package com.devesh.mvvmbasics

import android.app.Application
import com.devesh.mvvmbasics.di.AppComponent
import com.devesh.mvvmbasics.di.DaggerAppComponent

class MvvmBasics: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}