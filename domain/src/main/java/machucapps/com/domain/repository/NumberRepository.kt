package machucapps.com.domain.repository

import machucapps.com.domain.data.NumberText

interface NumberRepository {
    suspend fun getNumberText(number: Int): NumberText
}