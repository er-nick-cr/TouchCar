package com.example.touchcar.presentation.choose_part

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.ChoosePartFragmentBinding
import com.example.touchcar.domain.entity.Part
import com.example.touchcar.domain.entity.ToolbarHeader
import com.example.touchcar.presentation.CarSearchRouter
import com.example.touchcar.presentation.CarSearchRouterProvider
import com.example.touchcar.presentation.choose_part.recycler.ChoosePartAdapter
import com.example.touchcar.presentation.model.NetworkSource
import com.example.touchcar.presentation.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChoosePartFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChoosePartViewModel
    private lateinit var binding: ChoosePartFragmentBinding
    private lateinit var source: NetworkSource
    private val router: CarSearchRouter
        get() = (activity as CarSearchRouterProvider).router

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

        val chooseModelAdapter = ChoosePartAdapter(::onItemClick)
        val recyclerView: RecyclerView = binding.choosePartRecycler

        viewModel.partsLiveData
            .observe(this) {parts -> chooseModelAdapter.items = parts}
        viewModel.toolbarHeaderLiveData
            .observe(this) {value -> setToolbarFeatures(value)}

        recyclerView.adapter = chooseModelAdapter
        setDividerDecoration(recyclerView)

        viewModel.requestParts(source.url, source.type)
        viewModel.requestToolbarHeader(source.url)

        binding.searchPartBar.addTextChangedListener(
            afterTextChanged = { searchValue: Editable -> viewModel.searchModel(searchValue.toString()) }
        )
    }

    private fun setToolbarFeatures(toolbarHeader: ToolbarHeader) {
        val toolbar = binding.choosePartToolbar
        toolbar.navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.toolbar_back_button, null)
        toolbar.title = toolbarHeader.text
        toolbar.setNavigationOnClickListener {
            router.onBackPressed()
        }
    }

    private fun onItemClick(part: Part) {
        Log.d("url", part.partUrl)
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