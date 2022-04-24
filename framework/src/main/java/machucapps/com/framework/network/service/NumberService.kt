package machucapps.com.framework.network.service

import machucapps.com.framework.network.response.number.NumberDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface NumberService {

    @Headers("Content-Type: application/json")
    @GET(NUMBER_PATH)
    suspend fun getNumber(
        @Path(NUMBER) number: Int,
        // @Query("notfound") predictions: String = "floor" Uncomment to round down to the largest number that does have an associated fact :)
    ): NumberDTO

    companion object {
        private const val NUMBER = "number"
        const val NUMBER_PATH = "/{$NUMBER}"
    }
}