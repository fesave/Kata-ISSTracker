package machucapps.com.presentation.di

import machucapps.com.data.repository.ISSTrackerRepositoryImpl
import machucapps.com.data.repository.NumberRepositoryImpl
import machucapps.com.domain.repository.ISSTrackerRepository
import machucapps.com.domain.repository.NumberRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ISSTrackerRepository> { ISSTrackerRepositoryImpl(get()) }
    factory<NumberRepository> { NumberRepositoryImpl(get()) }
}