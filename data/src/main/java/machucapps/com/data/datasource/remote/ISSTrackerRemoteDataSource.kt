package machucapps.com.data.datasource.remote

import machucapps.com.domain.data.PassItem
import machucapps.com.domain.data.UserCoordinates

interface ISSTrackerRemoteDataSource {
    suspend fun getISSPasses(coordinates: UserCoordinates): List<PassItem>
}