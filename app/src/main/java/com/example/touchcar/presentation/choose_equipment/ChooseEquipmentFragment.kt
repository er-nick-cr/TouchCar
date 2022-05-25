package com.example.touchcar.presentation.choose_equipment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.ChooseEquipmentFragmentBinding
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.presentation.CarSearchRouter
import com.example.touchcar.presentation.CarSearchRouterProvider
import com.example.touchcar.presentation.choose_equipment.recycler.ChooseEquipmentAdapter
import com.example.touchcar.presentation.model.NetworkSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseEquipmentFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChooseEquipmentViewModel
    private lateinit var binding: ChooseEquipmentFragmentBinding
    private lateinit var source: NetworkSource
    private val router: CarSearchRouter
        get() = (activity as CarSearchRouterProvider).router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChooseEquipmentFragmentBinding.bind(
            inflater.inflate(
                R.layout.choose_equipment_fragment,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chooseEquipmentAdapter = ChooseEquipmentAdapter(::onItemClick)
        val recyclerView: RecyclerView = binding.equipmentSearchRecycler
        source = arguments?.get(SOURCE_ARG) as NetworkSource

        setToolbarNavigationButton()

        viewModel.equipmentLiveData
            .observe(this) {equipments -> chooseEquipmentAdapter.items = equipments }
        recyclerView.adapter = chooseEquipmentAdapter
        setDividerDecoration(recyclerView)
        viewModel.requestEquipments(source.url, source.type)
    }

    private fun setToolbarNavigationButton() {
        val toolbar = binding.chooseEquipmentToolbar
        toolbar.navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.toolbar_back_button, null)
        toolbar.setNavigationOnClickListener {
            router.onBackPressed()
        }
    }

    private fun onItemClick(equipment: Equipment) {
        router.next(this, source.copy(innerUrl = equipment.equipmentUrl))
    }

    private fun setDividerDecoration(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)

        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {

        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource): ChooseEquipmentFragment {
            return ChooseEquipmentFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }

}