package machucapps.com.presentation.ui.fragments.iss_list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import machucapps.com.domain.data.PassItem

@Parcelize
data class PassItemNav(
    val currentLocation: String,
    val passItem: @RawValue PassItem
) : Parcelable