package machucapps.com.framework.network

import machucapps.com.framework.network.service.NumberService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createNumberNetworkInstance(): NumberService {
    return Retrofit.Builder()
        .baseUrl(NUMBER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().build())
        .build()
        .create(NumberService::class.java)
}


