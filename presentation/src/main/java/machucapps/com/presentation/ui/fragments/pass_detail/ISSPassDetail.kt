package machucapps.com.presentation.ui.fragments.pass_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import machucapps.com.isstracker.databinding.IssPassDetailFragmentBinding
import machucapps.com.presentation.ui.ext.formatFutureDuration
import machucapps.com.presentation.ui.fragments.iss_list.PassItemNav
import org.koin.androidx.viewmodel.ext.android.viewModel

class ISSPassDetail : Fragment() {

    private val viewModel by viewModel<ISSPassDetailViewModel>()

    private val args by navArgs<ISSPassDetailArgs>()

    private var _binding: IssPassDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = IssPassDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(args.currentPass)
    }

    private fun setData(item: PassItemNav) {
        binding.header.textTrackerListStreetName.text = item.currentLocation
        binding.textPassDetailDurationValue.text =
            item.passItem.duration.formatFutureDuration(requireContext())
        binding.textPassDetailSecondsValue.text =
            viewModel.calculateDifferenceInSeconds(item.passItem.riseTime).toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}