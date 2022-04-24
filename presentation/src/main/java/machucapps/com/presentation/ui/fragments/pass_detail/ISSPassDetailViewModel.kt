package machucapps.com.presentation.ui.fragments.pass_detail

import androidx.lifecycle.ViewModel

class ISSPassDetailViewModel(
) : ViewModel() {

    fun calculateDifferenceInSeconds(passTime: Int): Long {
        val currentTime = System.currentTimeMillis().div(1000L)
        return passTime.minus(currentTime)
    }
}