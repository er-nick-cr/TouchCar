package com.example.touchcar.presentation.choose_equipment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.touchcar.R
import com.example.touchcar.databinding.ChooseEquipmentFragmentBinding
import com.example.touchcar.presentation.choose_body.ChooseBodyFragment
import com.example.touchcar.presentation.model.NetworkSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseEquipmentFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChooseEquipmentViewModel
    private lateinit var binding: ChooseEquipmentFragmentBinding
    private lateinit var source: NetworkSource

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

        source = arguments?.get(SOURCE_ARG) as NetworkSource

        viewModel.equipmentLiveData
            .observe(this) {equipments -> Log.d("eq", equipments[0].equipmentName)}

        viewModel.equipments

        viewModel.requestEquipments(source.url, source.type)
        Log.d("url", source.url)
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