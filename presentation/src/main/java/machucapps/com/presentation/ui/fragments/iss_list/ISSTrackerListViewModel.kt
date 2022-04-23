package machucapps.com.presentation.ui.fragments.iss_list

import android.location.Geocoder
import androidx.lifecycle.ViewModel
import machucapps.com.framework.datasource.provider.ContextProvider
import machucapps.com.isstracker.R
import java.util.*

class ISSTrackerListViewModel(
    private val contextProvider: ContextProvider
) : ViewModel() {

    fun getStreetName(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(contextProvider.getContext(), Locale.getDefault())
        val address = geocoder.getFromLocation(latitude, longitude, 1).first()
            .getAddressLine(0)
            .toString()
        return contextProvider.getCustomString(R.string.current_street_name, address)
    }
}