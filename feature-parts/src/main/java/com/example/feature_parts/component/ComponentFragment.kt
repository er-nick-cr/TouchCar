package com.example.feature_parts.component

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.with
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.with
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.core_common.NetworkSource
import com.example.core_data.domain.entity.ComponentImageSize
import com.example.core_data.domain.entity.Coordinates
import com.example.core_data.domain.entity.Item
import com.example.feature_parts.GlideApp
import com.example.feature_parts.R
import com.example.feature_parts.databinding.ComponentFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ComponentFragment : Fragment() {

    @Inject
    lateinit var viewModel: ComponentViewModel
    private lateinit var binding: ComponentFragmentBinding
    private lateinit var source: NetworkSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ComponentFragmentBinding.bind(inflater.inflate(R.layout.component_fragment, container, false))
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility", "CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        source = arguments?.get(SOURCE_ARG) as NetworkSource

        viewModel.componentLiveData.observe(this) {
            component ->
            GlideApp.with(this).asBitmap().load(component.imageUrl).listener(object : RequestListener<Bitmap> {
                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {

                    if (resource != null) {
                        binding.componentImage.image = resource
                        binding.componentImage.invalidate()
                    }
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).submit()
            binding.componentImage.imageSize = component.componentImageSize
            binding.componentImage.items = convertCoordinates(binding.componentImage, component.items, component.componentImageSize)
        }

        viewModel.requestComponent(source.url, source.baseUrl, source.innerUrl)


    }

    private fun convertCoordinates(view: View, items: List<Item>, imageSize: ComponentImageSize) : List<Coordinates> {
        return items.map { item ->
            Coordinates(
                x1 = item.coordinates.x1,
                y1 = item.coordinates.y1,
                x2 = item.coordinates.x2,
                y2 = item.coordinates.y2
            )
        }
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