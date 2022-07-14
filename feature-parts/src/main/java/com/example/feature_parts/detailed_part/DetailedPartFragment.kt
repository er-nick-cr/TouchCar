package com.example.feature_parts.detailed_part

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.core_common.NetworkSource
import com.example.feature_parts.R
import com.example.feature_parts.component.ComponentFragment
import com.example.feature_parts.databinding.DetailedPartFragmentBinding
import com.example.feature_parts.detailed_part.recycler.DetailedPartAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailedPartFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModel: DetailedPartViewModel
    lateinit var binding: DetailedPartFragmentBinding
    private lateinit var source: NetworkSource

    override fun getTheme() = R.style.AppDetailedPartItemsTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailedPartFragmentBinding.bind(inflater.inflate(R.layout.detailed_part_fragment, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DetailedPartAdapter()

        source = arguments?.get(SOURCE_ARG) as NetworkSource

        viewModel.detailedPartLiveData.observe(this) { detailedPart ->
            binding.detailedPartHeading.text = detailedPart.heading
            adapter.items = detailedPart.items
        }

        binding.detailedPartRecycler.adapter = adapter

        viewModel.requestDetailedPart(source.url, source.type)
    }

    companion object {

        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource): DetailedPartFragment {
            return DetailedPartFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }
}