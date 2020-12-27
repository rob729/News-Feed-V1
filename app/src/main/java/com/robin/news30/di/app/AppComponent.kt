package com.robin.news30.di.app

import com.robin.news30.di.activity.ActivityComponent
import com.robin.news30.di.activity.ActivityModule
import com.techyourchance.dagger2course.common.dependnecyinjection.app.AppScoped
import dagger.Component

@AppScoped
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent
}