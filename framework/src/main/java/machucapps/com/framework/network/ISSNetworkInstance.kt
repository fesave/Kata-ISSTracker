package machucapps.com.framework.network

import machucapps.com.framework.network.service.ISSTrackerService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createISSTrackerNetworkInstance(): ISSTrackerService {
    return Retrofit.Builder()
        .baseUrl(ISS_TRACKER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
        .create(ISSTrackerService::class.java)
}