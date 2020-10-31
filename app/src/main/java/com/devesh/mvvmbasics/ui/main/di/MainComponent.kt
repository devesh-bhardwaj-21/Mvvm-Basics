package com.devesh.mvvmbasics.ui.main.di

import com.devesh.mvvmbasics.di.ActivityScope
import com.devesh.mvvmbasics.ui.main.fragment.MainFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(fragment: MainFragment)
}