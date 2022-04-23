package machucapps.com.presentation.di

import machucapps.com.data.datasource.remote.ISSTrackerRemoteDataSource
import machucapps.com.framework.datasource.remote.ISSTrackerRemoteDataSourceImpl
import machucapps.com.framework.network.createNetworkInstance
import org.koin.dsl.module

val remoteModule = module {
    single { createNetworkInstance() }
    single<ISSTrackerRemoteDataSource> { ISSTrackerRemoteDataSourceImpl(get()) }
}