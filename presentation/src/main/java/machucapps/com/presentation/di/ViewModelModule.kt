package machucapps.com.presentation.di

import machucapps.com.presentation.ui.fragments.iss_list.ISSTrackerListViewModel
import machucapps.com.presentation.ui.fragments.pass_detail.ISSPassDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ISSTrackerListViewModel(get(), get()) }
    viewModel { ISSPassDetailViewModel(get(), get()) }
}