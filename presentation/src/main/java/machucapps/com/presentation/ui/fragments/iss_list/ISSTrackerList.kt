package machucapps.com.presentation.ui.fragments.iss_list

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import kotlinx.coroutines.flow.collect
import machucapps.com.domain.data.PassItem
import machucapps.com.isstracker.R
import machucapps.com.isstracker.databinding.IssTrackerListFragmentBinding
import machucapps.com.presentation.ui.ext.makeToast
import machucapps.com.presentation.ui.ext.navigateTo
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { value: ISSPassesState -> setData(value) }
        }
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
                    makeToast(R.string.coordinates_not_found)
                } else {
                    viewModel.getISSPasses(location.latitude, location.longitude)
                    setHeaderData(location.latitude, location.longitude)
                }
            }
        } else {
            requestLocationPermission()
        }
    }

    private fun setHeaderData(latitude: Double, longitude: Double) {
        binding.header.textTrackerListStreetName.text =
            viewModel.getUserStreetLocation(latitude, longitude)
    }

    private fun setData(state: ISSPassesState) {

        binding.progressBar.isVisible = state.isLoading

        if (state.error.isNotEmpty()) {
            makeToast(state.error)
        }

        if (state.passes.isNotEmpty()) {
            setRecyclerViewAdapter(state.passes)
        }
    }

    private fun setRecyclerViewAdapter(passes: List<PassItem>) {
        val adapter = ISSTrackerAdapter(requireContext()) { passItem ->
            navigateToDetailScreen(passItem)
        }.apply {
            setData(passes)
        }

        binding.list.apply {
            setAdapter(adapter)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigateToDetailScreen(passItem: PassItem) {
        navigateTo(
            ISSTrackerListDirections.actionISSTrackerListToISSPassDetail(
                PassItemNav(
                    currentLocation = viewModel.currentUserLocation,
                    passItem = passItem
                )
            )
        )
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