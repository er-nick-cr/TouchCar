package com.example.touchcar.presentation.choose_model

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.ChooseModelFragmentBinding
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.domain.entity.Model
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.choose_model.recycler.ChooseModelAdapter
import com.example.touchcar.presentation.navigation.ChooseMarketNavigator
import com.example.touchcar.presentation.navigation.ChooseModelNavigator
import com.example.touchcar.presentation.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseModelFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChooseModelViewModel
    lateinit var binding: ChooseModelFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        val recyclerView: RecyclerView = view.findViewById(R.id.model_search_recycler)!!
        val modelUrl: String = arguments?.get(ARG_URL) as String

        viewModel.modelLiveData
            .observe(this) { models -> chooseModelAdapter.models = models }
        recyclerView.adapter = chooseModelAdapter
        setDividerDecoration(recyclerView)
        viewModel.requestModels(modelUrl)
        binding.searchModelBar.addTextChangedListener(
            afterTextChanged = { searchValue: Editable -> viewModel.searchModel(searchValue.toString()) }
        )
    }

    private fun onItemClick(model: Model) {
        val navigator = activity as ChooseModelNavigator
        navigator.openChooseBody(model.bodyUrl)
    }

    private fun setDividerDecoration(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)

        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {

        private const val ARG_URL = "url"

        fun newInstance(url: String): ChooseModelFragment {
            return ChooseModelFragment().apply {
                arguments = bundleOf(ARG_URL to url)
            }
        }
    }
}