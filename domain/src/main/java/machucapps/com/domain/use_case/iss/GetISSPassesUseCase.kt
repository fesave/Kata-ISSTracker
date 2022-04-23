package machucapps.com.domain.use_case.iss

import machucapps.com.domain.BaseUseCase
import machucapps.com.domain.data.PassItem
import machucapps.com.domain.data.UserCoordinates
import machucapps.com.domain.repository.ISSTrackerRepository

class GetISSPassesUseCase(
    private val repository: ISSTrackerRepository
) : BaseUseCase<List<PassItem>, GetISSPassesUseCase.Params>() {

    override suspend fun call(params: Params): List<PassItem> {
        return repository.getISSPasses(params.coordinates)
    }

    data class Params(val coordinates: UserCoordinates)
}