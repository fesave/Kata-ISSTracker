package machucapps.com.presentation.di

import machucapps.com.presentation.ui.fragments.iss_list.ISSTrackerListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ISSTrackerListViewModel(get()) }
}