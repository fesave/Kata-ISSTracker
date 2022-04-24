package machucapps.com.presentation.di

import machucapps.com.domain.use_case.iss.GetISSPassesUseCase
import machucapps.com.domain.use_case.number.GetNumberTextUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetISSPassesUseCase(get()) }
    factory { GetNumberTextUseCase(get()) }
}