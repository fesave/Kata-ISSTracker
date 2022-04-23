package machucapps.com.data.repository

import machucapps.com.data.datasource.remote.ISSTrackerRemoteDataSource
import machucapps.com.domain.data.PassItem
import machucapps.com.domain.data.UserCoordinates
import machucapps.com.domain.repository.ISSTrackerRepository

class ISSTrackerRepositoryImpl(
    private val remoteDataSource: ISSTrackerRemoteDataSource
) : ISSTrackerRepository {
    override suspend fun getISSPasses(coordinates: UserCoordinates): List<PassItem> =
        remoteDataSource.getISSPasses(coordinates)
}