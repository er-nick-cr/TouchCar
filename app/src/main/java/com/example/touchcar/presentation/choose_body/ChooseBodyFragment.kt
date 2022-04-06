package com.example.touchcar.presentation.choose_body

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
import com.example.touchcar.databinding.ChooseBodyFragmentBinding
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.presentation.choose_body.recycler.ChooseBodyAdapter
import com.example.touchcar.presentation.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseBodyFragment : Fragment() {

    @Inject
    lateinit var chooseBodyViewModel: ChooseBodyViewModel
    lateinit var binding: ChooseBodyFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        val recyclerView: RecyclerView = view.findViewById(R.id.body_search_recycler)!!
        val bodyUrl: String = arguments?.get(ARG_URL) as String

        chooseBodyViewModel.bodyLiveData
            .observe(this) { models ->
                chooseBodyAdapter.bodyList = models
            }
        recyclerView.adapter = chooseBodyAdapter
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
        chooseBodyViewModel.getBodyList(bodyUrl)
        binding.searchBodyBar.addTextChangedListener(
            afterTextChanged = { s: Editable ->
                chooseBodyViewModel.searchMarket(s)
            }
        )
    }

    private fun onItemClick(body: Body) {
        Log.d("tag", body.equipmentUrl)
    }

    companion object {

        private const val ARG_URL = "url"
        fun newInstance(url: String): ChooseBodyFragment {
            return ChooseBodyFragment().apply {
                arguments = bundleOf(ARG_URL to url)
            }
        }
    }

}