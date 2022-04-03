package com.example.touchcar.presentation.choose_model

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.presentation.choose_model.recycler.ChooseModelAdapter
import com.example.touchcar.presentation.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseModelFragment : Fragment() {

    @Inject
    lateinit var chooseModelViewModel: ChooseModelViewModel
    lateinit var modelUrl: String
    private lateinit var chooseModelAdapter: ChooseModelAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText

    companion object {
        fun newInstance() = ChooseModelFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_model_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        modelUrl = arguments?.get("url") as String
        chooseModelViewModel.modelLiveData
            .observe(
                this, { models ->
                    chooseModelAdapter.models = models
                    searchBar.addTextChangedListener(
                        afterTextChanged = { s: Editable ->
                            chooseModelAdapter.models = models.filter {
                                it.modelName.contains(
                                    s.toString(),
                                    ignoreCase = true
                                )
                            }
                        })
                })
        chooseModelAdapter = ChooseModelAdapter()
        recyclerView = view.findViewById(R.id.model_search_recycler)!!
        recyclerView.adapter = chooseModelAdapter
        chooseModelViewModel.getModels(modelUrl)
        searchBar = view.findViewById(R.id.search_model_bar)
    }


}