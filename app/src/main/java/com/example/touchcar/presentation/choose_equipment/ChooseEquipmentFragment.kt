package com.example.touchcar.presentation.choose_equipment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.touchcar.R

class ChooseEquipmentFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseEquipmentFragment()
    }

    private lateinit var viewModel: ChooseEquipmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_equipment_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChooseEquipmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}