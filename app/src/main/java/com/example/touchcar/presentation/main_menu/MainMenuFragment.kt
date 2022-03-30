package com.example.touchcar.presentation.main_menu

import android.graphics.Insets.add
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.main_menu.bottom_sheet.BottomSheetFragment
import com.example.touchcar.presentation.main_menu.recycler.MainMenuAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



@AndroidEntryPoint
class MainMenuFragment : Fragment() {
    private lateinit var mainMenuAdapter: MainMenuAdapter
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var mainMenuViewModel: MainMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        mainMenuViewModel.manufacturerLiveData
            .observe(
                this, { manufacturers ->
                    mainMenuAdapter.manufacturers = manufacturers
                })
        mainMenuAdapter = MainMenuAdapter(::onItemClick);
        recyclerView = view?.findViewById(R.id.manufacturer_search_recycler)!!
        recyclerView.adapter = mainMenuAdapter

        mainMenuViewModel.getManufacturers();


    }

    private fun onItemClick(manufacturer: Manufacturer) {
        val bottomSheetFragment = BottomSheetFragment()
            childFragmentManager.beginTransaction()
                .add(bottomSheetFragment, "tag")
                .commit()
    }

    companion object {

    }
}