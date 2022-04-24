package machucapps.com.presentation.ui.fragments.pass_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import machucapps.com.domain.UseCaseResult
import machucapps.com.domain.use_case.number.GetNumberTextUseCase
import machucapps.com.framework.provider.ContextProvider
import machucapps.com.isstracker.R

class ISSPassDetailViewModel(
    private val contextProvider: ContextProvider,
    private val getNumberTextUseCase: GetNumberTextUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NumberState())
    val state: StateFlow<NumberState> = _state

    fun calculateDifferenceInSeconds(passTime: Int): Long {
        val currentTime = System.currentTimeMillis().div(1000L)
        return passTime.minus(currentTime)
    }

    fun generateNumberText(number: Int, trivia: String): String {
        return contextProvider.getCustomDetailString(
            R.string.iss_detail_number_text,
            number,
            trivia
        )
    }

    fun getNumberText(number: Int) {
        _state.value = NumberState(isLoading = true)
        viewModelScope.launch {
            getNumberTextUseCase.execute(
                GetNumberTextUseCase.Params(number)
            ).collect { result ->
                when (result) {
                    is UseCaseResult.Success -> {
                        _state.value = NumberState(text = result.data.text)
                    }
                    is UseCaseResult.Error -> {
                        _state.value =
                            NumberState(
                                error = result.throwable.message
                                    ?: contextProvider.getString(R.string.unknown_error)
                            )
                    }
                }
            }
        }
    }
}

data class NumberState(
    val isLoading: Boolean = false,
    val text: String = "",
    val error: String = ""
)