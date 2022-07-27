package com.example.feature_car_search.presentation.choose_model

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
import com.example.core_data.domain.entity.Model
import com.example.feature_car_search.R
import com.example.feature_car_search.databinding.ChooseModelFragmentBinding
import com.example.feature_car_search.presentation.choose_model.recycler.ChooseModelAdapter
import com.example.feature_car_search.router.CarSearchRouter
import com.example.feature_car_search.router.CarSearchRouterProvider
import com.example.core_common.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseModelFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChooseModelViewModel
    private lateinit var binding: ChooseModelFragmentBinding
    private lateinit var source: NetworkSource
    private val router: CarSearchRouter
        get() = (activity as CarSearchRouterProvider).router


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChooseModelFragmentBinding.bind(
            inflater.inflate(
                R.layout.choose_model_fragment,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chooseModelAdapter = ChooseModelAdapter(::onItemClick)
        val recyclerView: RecyclerView = binding.modelSearchRecycler
        source = arguments?.get(SOURCE_ARG) as NetworkSource

        setToolbarNavigationButton()

        viewModel.modelLiveData
            .observe(viewLifecycleOwner) { models -> chooseModelAdapter.items = models }

        recyclerView.adapter = chooseModelAdapter
        setDividerDecoration(recyclerView)
        viewModel.requestModels(source.url)

        binding.searchModelBar.addTextChangedListener(
            afterTextChanged = { searchValue: Editable -> viewModel.searchModel(searchValue.toString()) }
        )
    }

    private fun setToolbarNavigationButton() {
        with(binding.chooseModelToolbar) {
            navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.toolbar_back_button, null)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

    private fun onItemClick(model: Model) {
        router.next(this, source.copy(innerUrl = model.bodyUrl))
    }

    private fun setDividerDecoration(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)

        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {

        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource): ChooseModelFragment {
            return ChooseModelFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }
}