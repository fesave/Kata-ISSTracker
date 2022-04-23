package machucapps

import android.app.Application
import machucapps.com.presentation.di.commonModule
import machucapps.com.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ISSTracker : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ISSTracker)

            modules(
                listOf(
                    commonModule,
                    viewModelModule
                )
            )
        }
    }
}