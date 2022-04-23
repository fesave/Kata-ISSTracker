package machucapps.com.domain.repository

import machucapps.com.domain.data.PassItem
import machucapps.com.domain.data.UserCoordinates

interface ISSTrackerRepository {
    suspend fun getISSPasses(coordinates: UserCoordinates): List<PassItem>
}