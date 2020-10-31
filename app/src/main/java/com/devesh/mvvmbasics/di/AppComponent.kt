package com.devesh.mvvmbasics.di

import android.content.Context
import com.devesh.mvvmbasics.data.api.di.RetrofitModule
import com.devesh.mvvmbasics.data.room.RoomModule
import com.devesh.mvvmbasics.ui.main.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubComonents::class, RetrofitModule::class, RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory

}