package com.example.feature_car_search.presentation.choose_body

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
import com.example.feature_car_search.router.CarSearchRouter
import com.example.feature_car_search.router.CarSearchRouterProvider
import com.example.core_data.domain.entity.Body
import com.example.feature_car_search.R
import com.example.feature_car_search.databinding.ChooseBodyFragmentBinding
import com.example.feature_car_search.presentation.choose_body.recycler.ChooseBodyAdapter
import com.example.core_common.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseBodyFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChooseBodyViewModel
    private lateinit var binding: ChooseBodyFragmentBinding
    private lateinit var source: NetworkSource
    private val router: CarSearchRouter
        get() = (activity as CarSearchRouterProvider).router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChooseBodyFragmentBinding.bind(
            inflater.inflate(
                R.layout.choose_body_fragment,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chooseBodyAdapter = ChooseBodyAdapter(::onItemClick)
        val recyclerView: RecyclerView = binding.bodySearchRecycler
        source = arguments?.get(SOURCE_ARG) as NetworkSource

        setToolbarNavigationButton()

        viewModel.bodyLiveData
            .observe(viewLifecycleOwner) { models -> chooseBodyAdapter.items = models }
        recyclerView.adapter = chooseBodyAdapter
        setDividerDecoration(recyclerView)
        viewModel.requestBodyList(source.url)
        binding.searchBodyBar.addTextChangedListener(
            afterTextChanged = { searchValue: Editable -> viewModel.searchMarket(searchValue.toString()) }
        )
    }

    private fun setToolbarNavigationButton() {
        with(binding.chooseBodyToolbar) {
            navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.toolbar_back_button, null)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

    private fun onItemClick(body: Body) {
        router.next(this, source.copy(innerUrl = body.equipmentUrl))
    }

    private fun setDividerDecoration(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)

        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {

        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource): ChooseBodyFragment {
            return ChooseBodyFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }

}