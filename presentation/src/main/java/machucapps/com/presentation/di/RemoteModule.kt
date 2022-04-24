package machucapps.com.presentation.di

import machucapps.com.data.datasource.remote.ISSTrackerRemoteDataSource
import machucapps.com.data.datasource.remote.NumberRemoteDataSource
import machucapps.com.framework.datasource.remote.ISSTrackerRemoteDataSourceImpl
import machucapps.com.framework.datasource.remote.NumberRemoteDataSourceImpl
import machucapps.com.framework.network.createISSTrackerNetworkInstance
import machucapps.com.framework.network.createNumberNetworkInstance
import org.koin.dsl.module

val remoteModule = module {
    single { createISSTrackerNetworkInstance() }
    single { createNumberNetworkInstance() }
    single<ISSTrackerRemoteDataSource> { ISSTrackerRemoteDataSourceImpl(get()) }
    single<NumberRemoteDataSource> { NumberRemoteDataSourceImpl(get()) }
}