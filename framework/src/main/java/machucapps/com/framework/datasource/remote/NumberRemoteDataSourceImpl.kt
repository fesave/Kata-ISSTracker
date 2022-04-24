package machucapps.com.framework.datasource.remote

import machucapps.com.data.datasource.remote.NumberRemoteDataSource
import machucapps.com.domain.data.NumberText
import machucapps.com.framework.network.response.number.toNumber
import machucapps.com.framework.network.service.NumberService

class NumberRemoteDataSourceImpl(
    private val numberService: NumberService
) : NumberRemoteDataSource {
    override suspend fun getNumberText(number: Int): NumberText =
        numberService.getNumber(number).toNumber()
}