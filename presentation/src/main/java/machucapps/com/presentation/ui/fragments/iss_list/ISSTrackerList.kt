package machucapps.com.presentation.ui.fragments.iss_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import machucapps.com.isstracker.databinding.IssTrackerListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ISSTrackerList : Fragment() {

    private val viewModel by viewModel<ISSTrackerListViewModel>()

    private var _binding: IssTrackerListFragmentBinding? = null
    private val binding get() = _binding!!

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
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}