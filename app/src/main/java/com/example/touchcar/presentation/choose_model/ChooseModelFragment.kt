package com.example.touchcar.presentation.choose_model

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.ChooseModelFragmentBinding
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.choose_model.recycler.ChooseModelAdapter
import com.example.touchcar.presentation.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseModelFragment : Fragment() {

    @Inject
    lateinit var chooseModelViewModel: ChooseModelViewModel
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

        val chooseModelAdapter = ChooseModelAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.model_search_recycler)!!
        val modelUrl: String = arguments?.get(ARG_URL) as String

        chooseModelViewModel.modelLiveData
            .observe(this) { models ->
                chooseModelAdapter.models = models
            }
        recyclerView.adapter = chooseModelAdapter
        chooseModelViewModel.getModels(modelUrl)
        binding.searchModelBar.addTextChangedListener(
            afterTextChanged = { s: Editable -> chooseModelViewModel.searchModel(s) }
        )
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