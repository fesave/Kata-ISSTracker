package machucapps.com.framework.datasource.remote

import machucapps.com.data.datasource.remote.ISSTrackerRemoteDataSource
import machucapps.com.domain.data.PassItem
import machucapps.com.domain.data.UserCoordinates
import machucapps.com.framework.network.response.toPassItem
import machucapps.com.framework.network.service.ISSTrackerService

class ISSTrackerRemoteDataSourceImpl(
    private val service: ISSTrackerService
) : ISSTrackerRemoteDataSource {
    override suspend fun getISSPasses(coordinates: UserCoordinates): List<PassItem> =
        service.getISSPasses(coordinates.latitude, coordinates.longitude).response.map {
            it.toPassItem()
        }
}