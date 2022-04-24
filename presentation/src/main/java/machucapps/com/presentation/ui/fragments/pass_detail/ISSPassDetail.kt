package machucapps.com.presentation.ui.fragments.pass_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import machucapps.com.isstracker.databinding.IssPassDetailFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ISSPassDetail : Fragment() {

    private val viewModel by viewModel<ISSPassDetailViewModel>()

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
    }
}