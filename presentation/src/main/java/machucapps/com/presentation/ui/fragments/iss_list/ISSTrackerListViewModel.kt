package machucapps.com.presentation.ui.fragments.iss_list

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import machucapps.com.domain.UseCaseResult
import machucapps.com.domain.data.PassItem
import machucapps.com.domain.data.UserCoordinates
import machucapps.com.domain.use_case.iss.GetISSPassesUseCase
import machucapps.com.framework.provider.ContextProvider
import machucapps.com.isstracker.R
import java.util.*

class ISSTrackerListViewModel(
    private val contextProvider: ContextProvider,
    private val getISSPassesUseCase: GetISSPassesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(PassesState())
    val state: StateFlow<PassesState> = _state

    fun getStreetName(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(contextProvider.getContext(), Locale.getDefault())
        val address = geocoder.getFromLocation(latitude, longitude, 1).first()
            .getAddressLine(0)
            .toString()
        return contextProvider.getCustomString(R.string.current_street_name, address)
    }

    fun getPasses(latitude: Double, longitude: Double) {
        _state.value = PassesState(isLoading = true)
        viewModelScope.launch {
            getISSPassesUseCase.execute(
                GetISSPassesUseCase.Params(
                    UserCoordinates(
                        latitude,
                        longitude
                    )
                )
            ).collect { result ->
                when (result) {
                    is UseCaseResult.Success -> {
                        _state.value = PassesState(passes = result.data)
                    }
                    is UseCaseResult.Error -> {
                        _state.value = PassesState(error = result.throwable.message ?: "Error desconocido")
                    }
                }

            }
        }
    }
}

data class PassesState(
    val isLoading: Boolean = false,
    val passes: List<PassItem> = emptyList(),
    val error: String = ""
)