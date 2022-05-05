package com.example.touchcar.presentation.car

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.touchcar.R
import com.example.touchcar.databinding.CarFragmentBinding
import com.example.touchcar.domain.entity.Car
import com.example.touchcar.presentation.model.NetworkSource
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

        source = arguments?.get(SOURCE_ARG) as NetworkSource
        viewModel.carLiveData
            .observe(this) { car -> setCarValues(car) }

        viewModel.requestCar(source.url, source.type)
    }

    private fun setCarValues(car: Car) {
        binding.carNameHeading.text = car.carName
        binding.carEquipmentName.text = car.equipmentFeature
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