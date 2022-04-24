package machucapps.com.data.repository

import machucapps.com.data.datasource.remote.NumberRemoteDataSource
import machucapps.com.domain.data.NumberText
import machucapps.com.domain.repository.NumberRepository

class NumberRepositoryImpl(
    private val numberRemoteDataSource: NumberRemoteDataSource
) : NumberRepository {
    override suspend fun getNumberText(number: Int): NumberText =
        numberRemoteDataSource.getNumberText(number)
}