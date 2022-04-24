package machucapps.com.domain.use_case.number

import machucapps.com.domain.BaseUseCase
import machucapps.com.domain.data.NumberText
import machucapps.com.domain.repository.NumberRepository

class GetNumberTextUseCase(
    private val repository: NumberRepository
) : BaseUseCase<NumberText, GetNumberTextUseCase.Params>() {

    override suspend fun call(params: Params): NumberText {
        return repository.getNumberText(params.number)
    }

    data class Params(val number: Int)
}