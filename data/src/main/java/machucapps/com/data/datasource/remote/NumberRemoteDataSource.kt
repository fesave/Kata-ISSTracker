package machucapps.com.data.datasource.remote

import machucapps.com.domain.data.NumberText

interface NumberRemoteDataSource {
    suspend fun getNumberText(number: Int): NumberText
}