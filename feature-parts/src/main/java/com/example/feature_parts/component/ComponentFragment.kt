package com.example.feature_parts.component

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.core_common.NetworkSource
import com.example.core_common.SelectedCoordinates
import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.entity.Item
import com.example.feature_parts.GlideApp
import com.example.feature_parts.R
import com.example.feature_parts.component.items_recycler.ComponentItemsAdapter
import com.example.feature_parts.component.selector_recycler.SelectorAdapter
import com.example.feature_parts.databinding.ComponentFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ComponentFragment : Fragment() {

    @Inject
    lateinit var viewModel: ComponentViewModel
    private val componentItemAdapter = ComponentItemsAdapter(::onItemClick)
    private lateinit var binding: ComponentFragmentBinding
    private lateinit var source: NetworkSource
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ComponentFragmentBinding.bind(
            inflater.inflate(
                R.layout.component_fragment,
                container,
                false
            )
        )
        bottomSheetBehavior = BottomSheetBehavior.from(binding.componentBottomSheet)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectorAdapter = SelectorAdapter(::onSelectorItemClick)
        val recyclerView = binding.componentRecycler

        setBottomSheetOptions()
        source = arguments?.get(SOURCE_ARG) as NetworkSource

        viewModel.componentsLiveData.observe(this) {components ->
            if (components.size > 1) {
                selectorAdapter.items = components
            } else {
                binding.selectorRecycler.visibility = View.GONE
            }
        }

        binding.selectorRecycler.adapter = selectorAdapter

        viewModel.currentComponentLiveData.observe(this) { component ->
            setToolbarFeatures(component.header)
            loadImage(component)
            componentItemAdapter.items = component.items
            if(component.items.size > 3) {
                binding.componentBottomSheet.layoutParams.height = 1200
            }
        }

        recyclerView.adapter = componentItemAdapter
        setDividerDecoration(recyclerView)

        viewModel.requestComponent(source.url, source.baseUrl, source.innerUrl, source.type)
    }

    private fun setToolbarFeatures(header: String) {
        with(binding.componentToolbar) {
            navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.toolbar_back_button, null)
            setNavigationOnClickListener { activity?.onBackPressed() }
            title = header
        }
    }

    private fun setBottomSheetOptions() {
        with(bottomSheetBehavior) {
            isHideable = false
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    binding.bottomSheetOpenArrow.rotation = slideOffset * -180
                }
            })
        }
    }

    private fun setDividerDecoration(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)

        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    fun onClickImage(selectedCoordinates: SelectedCoordinates) {
        viewModel.itemsLiveData.observe(this) { items ->
            val index = items.indexOfFirst {item ->  selectedCoordinates.x in item.coordinates.x1..item.coordinates.x2 &&
                        selectedCoordinates.y in item.coordinates.y1..item.coordinates.y2 }
            if (index != -1) {
                componentItemAdapter.onItemSelectedByCoordinates(index)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                binding.componentRecycler.scrollToPosition(index)
            }
        }
    }

    private fun onSelectorItemClick(index: Int) {
        viewModel.onComponentSelected(index)
    }

    private fun onItemClick(item: Item) {
        Log.d("item", item.itemName)
    }

    private fun loadImage(component: Component) {
        GlideApp.with(this)
            .asBitmap()
            .load(component.imageUrl)
            .listener(object : RequestListener<Bitmap> {
                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (resource != null) {
                        with(binding.componentImage) {
                            image = resource
                            items = viewModel.convertCoordinates(
                                resource,
                                component.items,
                                component.componentImageSize
                            )
                            onClickImage = ::onClickImage
                        }
                    }
                    return true
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    e?.printStackTrace()
                    return false
                }
            }).submit()
    }

    companion object {

        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource): ComponentFragment {
            return ComponentFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }

}