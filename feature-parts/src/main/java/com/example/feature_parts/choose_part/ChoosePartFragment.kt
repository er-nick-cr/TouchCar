package com.example.feature_parts.choose_part

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.core_common.NetworkSource
import com.example.core_common_navigation.navigation.PartsNavigator
import com.example.core_data.domain.entity.Part
import com.example.feature_parts.choose_part.recycler.ChoosePartAdapter
import com.example.feature_parts.R
import com.example.feature_parts.databinding.ChoosePartFragmentBinding
import com.example.touchcar.presentation.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChoosePartFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChoosePartViewModel
    private lateinit var binding: ChoosePartFragmentBinding
    private lateinit var source: NetworkSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChoosePartFragmentBinding.bind(
            inflater.inflate(
                R.layout.choose_part_fragment,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        source = arguments?.get(SOURCE_ARG) as NetworkSource

        val choosePartAdapter = ChoosePartAdapter(::onItemClick)
        val recyclerView: RecyclerView = binding.choosePartRecycler

        viewModel.partsLiveData
            .observe(this) {parts -> choosePartAdapter.items = parts}
        viewModel.headerLiveData
            .observe(this) {value -> setToolbarFeatures(value)}

        recyclerView.adapter = choosePartAdapter
        setDividerDecoration(recyclerView)

        viewModel.requestParts(source.url, source.type)

        binding.searchPartBar.addTextChangedListener(
            afterTextChanged = { searchValue: Editable -> viewModel.searchPart(searchValue.toString()) }
        )
    }

    private fun setToolbarFeatures(header: String) {
        with(binding.choosePartToolbar) {
            navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.toolbar_back_button, null)
            setNavigationOnClickListener { activity?.onBackPressed() }
            title = header
        }
    }

    private fun onItemClick(part: Part) {
        val partsNavigator = activity as PartsNavigator
        partsNavigator.openComponentFragment(source.copy(innerUrl = part.partUrl))

    }

    private fun setDividerDecoration(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)

        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {

        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource): ChoosePartFragment {
            return ChoosePartFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }
}