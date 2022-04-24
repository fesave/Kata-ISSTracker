package machucapps.com.presentation.ui.fragments.pass_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.collect
import machucapps.com.isstracker.R
import machucapps.com.isstracker.databinding.IssPassDetailFragmentBinding
import machucapps.com.presentation.ui.ext.formatDuration
import machucapps.com.presentation.ui.ext.makeToast
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

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { value: NumberState -> setFooterText(value) }
        }
    }

    private fun setFooterText(state: NumberState) {
        if (state.error.isNotEmpty()) {
            makeToast(state.error)
        }

        if (state.text.isNotEmpty()) {
            val number = args.currentPass.passItem.duration
            binding.textPassDetailNumberText.text = viewModel.generateNumberText(number, state.text)
        }
    }

    private fun setData(item: PassItemNav) {
        viewModel.getNumberText(item.passItem.duration)
        binding.header.textTrackerListStreetName.text = item.currentLocation
        binding.textPassDetailDurationValue.text =
            item.passItem.duration.formatDuration(requireContext().getString(R.string.iss_duration_time_detail))
        binding.textPassDetailSecondsValue.text =
            viewModel.calculateDifferenceInSeconds(item.passItem.riseTime).toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}