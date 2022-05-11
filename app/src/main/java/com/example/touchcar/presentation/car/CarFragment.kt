package com.example.touchcar.presentation.car

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.CarFragmentBinding
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.Part
import com.example.touchcar.presentation.choose_body.recycler.CarParametersAdapter
import com.example.touchcar.presentation.choose_body.recycler.PartsAdapter
import com.example.touchcar.presentation.model.NetworkSource
import com.example.touchcar.presentation.utils.getLogo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarFragment : Fragment() {

    @Inject
    lateinit var viewModel: CarViewModel
    private lateinit var binding: CarFragmentBinding
    private lateinit var source: NetworkSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CarFragmentBinding.bind(inflater.inflate(R.layout.car_fragment, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val carParametersAdapter = CarParametersAdapter()
        val partsAdapter = PartsAdapter(::onItemClick)
        val recyclerViewParameters: RecyclerView = binding.carParametersRecycler
        val recyclerViewParts: RecyclerView = binding.carPartsRecycler

        source = arguments?.get(SOURCE_ARG) as NetworkSource
        binding.carLabelImage.setImageResource(source.type.getLogo())

        viewModel.carLiveData
            .observe(this) { car ->
                setCarValues(car)
                carParametersAdapter.carParameters = car.parameters
                partsAdapter.parts = car.parts
            }

        recyclerViewParameters.adapter = carParametersAdapter
        recyclerViewParts.adapter = partsAdapter

        viewModel.requestCar(source.url, source.type)
    }

    private fun setCarValues(car: Car) {
        binding.carNameHeading.text = car.carName
        binding.carEquipmentName.text = car.equipmentFeature
    }

    private fun onItemClick(part: Part) {
        Log.d("part_url", part.partUrl)
    }

    companion object {
        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource):CarFragment {
            return CarFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }
}