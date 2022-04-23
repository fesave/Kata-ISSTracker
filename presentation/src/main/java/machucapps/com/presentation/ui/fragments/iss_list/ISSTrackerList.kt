package machucapps.com.presentation.ui.fragments.iss_list

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import machucapps.com.isstracker.R
import machucapps.com.isstracker.databinding.IssTrackerListFragmentBinding
import machucapps.com.presentation.ui.ext.makeToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ISSTrackerList : Fragment(), EasyPermissions.PermissionCallbacks {

    private val viewModel by viewModel<ISSTrackerListViewModel>()

    private var _binding: IssTrackerListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = IssTrackerListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        requestLocationPermission()
    }

    private fun hasLocationPermission() =
        EasyPermissions.hasPermissions(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)

    private fun requestLocationPermission() {
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.location_permissions_message),
            PERMISSION_LOCATION_REQUEST_CODE,
            PERMISSION
        )
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        Log.d(TAG, "Permission not granted")
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestLocationPermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        getCurrentCoordinates()
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentCoordinates() {
        if (hasLocationPermission()) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                if (location == null) {
                    makeToast(R.string.location_not_found)
                } else {
                    val userLocation = Location("").apply {
                        latitude = location.latitude
                        longitude = location.longitude

                    }
                    val geocoder = Geocoder(requireContext(), Locale.getDefault())
                    val addresses = geocoder.getFromLocation(userLocation.latitude, userLocation.longitude, 1)

                    Log.d(TAG, "Lat: ${userLocation.latitude}, Long: ${userLocation.longitude}")
                    Log.d(TAG, "Address: ${addresses.first().getAddressLine(0)}")
                }
            }

        } else {
            requestLocationPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        // The EasyPermissions library is not up to date and still needs this deprecated method.
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private const val PERMISSION_LOCATION_REQUEST_CODE = 1
        private const val PERMISSION = Manifest.permission.ACCESS_FINE_LOCATION
        private const val TAG = "ISSTrackerList"
    }
}