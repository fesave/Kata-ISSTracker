package machucapps.com.framework.network.service

import machucapps.com.framework.network.response.iss.ISSTrackerDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ISSTrackerService {

    @GET(ISS_PATH)
    suspend fun getISSPasses(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("n") predictions: Int = 10
    ): ISSTrackerDTO


    companion object {
        const val ISS_PATH = "/iss/"
    }
}