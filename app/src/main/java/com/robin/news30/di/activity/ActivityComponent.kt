package com.robin.news30.di.activity

import com.robin.news30.di.presentation.PresentationComponent
import com.techyourchance.dagger2course.common.dependnecyinjection.activity.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent
}