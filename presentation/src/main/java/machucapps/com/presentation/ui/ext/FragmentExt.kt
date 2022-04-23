package machucapps.com.presentation.ui.ext

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.makeToast(message: Int) {
    Toast.makeText(this.requireContext(), getString(message), Toast.LENGTH_LONG).show()
}

fun Fragment.makeToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_LONG).show()
}