package machucapps.com.presentation.ui.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.makeToast(message: Int) {
    Toast.makeText(this.requireContext(), getString(message), Toast.LENGTH_LONG).show()
}

fun Fragment.makeToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_LONG).show()
}

fun Fragment.navigateTo(direction: NavDirections) {
    findNavController().navigate(direction)
}