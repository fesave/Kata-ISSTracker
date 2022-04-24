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

    private val _state = MutableStateFlow(ISSPassesState())
    val state: StateFlow<ISSPassesState> = _state

    lateinit var currentUserLocation: String

    fun getUserStreetLocation(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(contextProvider.getContext(), Locale.getDefault())
        val address = geocoder.getFromLocation(latitude, longitude, 1).first()
            .getAddressLine(0)
            .toString()
        currentUserLocation = contextProvider.getCustomString(R.string.current_street_name, address)
        return currentUserLocation
    }

    fun getISSPasses(latitude: Double, longitude: Double) {
        _state.value = ISSPassesState(isLoading = true)
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
                        _state.value = ISSPassesState(passes = result.data)
                    }
                    is UseCaseResult.Error -> {
                        _state.value =
                            ISSPassesState(
                                error = result.throwable.message
                                    ?: contextProvider.getString(R.string.unknown_error)
                            )
                    }
                }

            }
        }
    }
}

data class ISSPassesState(
    val isLoading: Boolean = false,
    val passes: List<PassItem> = emptyList(),
    val error: String = ""
)