package com.robin.news30.di.presentation

import com.robin.news30.fragment.NewsListFragment
import com.robin.news30.fragment.NewsSourceFragment
import com.techyourchance.dagger2course.common.dependnecyinjection.presentation.PresentationScoped
import dagger.Subcomponent

@PresentationScoped
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {
    fun inject(newsSourceFragment: NewsSourceFragment)
    fun inject(newsListFragment: NewsListFragment)
}