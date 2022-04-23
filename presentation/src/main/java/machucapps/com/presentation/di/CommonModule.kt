package machucapps.com.presentation.di

import machucapps.com.framework.datasource.provider.ContextProvider
import machucapps.com.framework.datasource.provider.impl.ContextProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
    factory<ContextProvider> { ContextProviderImpl(androidContext()) }
}