package com.lucidware.fm_rt.presentation.application

import android.app.Application
import com.lucidware.fm_rt.presentation.application.di.ApplicationComponent
import com.lucidware.fm_rt.presentation.application.di.ApplicationGraph
import com.lucidware.fm_rt.presentation.application.di.HasComponent

open class FmrtApplication : Application(), HasComponent<ApplicationComponent> {

    lateinit internal var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = buildComponent()
    }

    open protected fun buildComponent(): ApplicationComponent {
        return ApplicationGraph(this).component
    }

    override fun getComponent(): ApplicationComponent {
        return applicationComponent
    }
}