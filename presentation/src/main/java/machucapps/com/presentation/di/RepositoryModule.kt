package machucapps.com.presentation.di

import machucapps.com.data.repository.ISSTrackerRepositoryImpl
import machucapps.com.domain.repository.ISSTrackerRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ISSTrackerRepository> { ISSTrackerRepositoryImpl(get()) }
}