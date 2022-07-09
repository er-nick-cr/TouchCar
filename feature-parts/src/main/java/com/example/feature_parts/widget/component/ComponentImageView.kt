package com.example.feature_parts.widget.component

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.entity.ComponentImageSize
import com.example.core_data.domain.entity.ComponentPart
import com.example.core_data.domain.entity.Coordinates
import com.example.feature_parts.GlideApp
import com.example.feature_parts.R

class ComponentImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyle) {

    var items: List<Coordinates> = emptyList()
    var image: Bitmap? = null
    var onClickImage: ((SelectedCoordinates) -> Unit)? = null
    private var ratio: Float = 1f
    private var coordinateExpander = 5f
    private val paint = Paint()
    private var lastFocusX = 0f
    private var lastFocusY = 0f
    private var selectedCoordinates = SelectedCoordinates(0f, 0f)
    private var drawMatrix = Matrix()
    private var inverseDrawMatrix = Matrix()
    private var centreX = 0f
    private var centreY = 0f
    private val scaleListener = ScaleListener()
    private val gestureListener = GestureListener()
    private val scaleDetector = ScaleGestureDetector(context, scaleListener)
    private val gestureDetector = GestureDetector(context, gestureListener)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.apply {
            with(paint) {
                color = ContextCompat.getColor(context, R.color.component_item_stroke)
                strokeWidth = 3F
                style = Paint.Style.STROKE
            }

            if (image != null) {
                centreX = ((width - image!!.width) / 2).toFloat()
                centreY = ((height - image!!.height) / 2).toFloat()
                setMatrix(drawMatrix)
                translate(centreX, centreY)
                drawBitmap(image!!, 0F, 0F, null)
                items.forEach { item ->
                    canvas.drawRoundRect(item.x1, item.y1, item.x2, item.y2, 20F, 20F, paint)
                }
            }
        }
        invalidate()
    }

    fun loadImage(component: Component) {
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
                        image = resource
                        items = convertCoordinates(
                            resource,
                            component.componentParts,
                            component.componentImageSize
                        )
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

    fun convertCoordinates(
        resource: Bitmap,
        componentParts: List<ComponentPart>,
        imageSize: ComponentImageSize
    ): List<Coordinates> {
        return componentParts.map { item ->
            val resourceRatio: Float = resource.width.toFloat() / resource.height.toFloat()
            val imageSizeRatio: Float = imageSize.width / imageSize.height
            ratio = if (imageSize.width == 1F && imageSize.height == 1F) {
                1f
            } else if (imageSize.height != 1F && imageSize.width == 1F) {
                resource.height.toFloat() / imageSize.height
            } else if (
                resource.width.toFloat() != imageSize.width &&
                resource.height.toFloat() != imageSize.height &&
                resourceRatio / imageSizeRatio == 1F || imageSize.width / imageSize.height == 1F
            ) {
                resource.width.toFloat() / imageSize.width
            } else {
                resourceRatio / imageSizeRatio
            }

            Coordinates(
                x1 = (item.coordinates.x1 * ratio) - coordinateExpander,
                y1 = (item.coordinates.y1 * ratio) - coordinateExpander,
                x2 = (item.coordinates.x2 * ratio) + coordinateExpander,
                y2 = (item.coordinates.y2 * ratio) + coordinateExpander
            )
        }
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            if (detector != null) {
                lastFocusX = detector.focusX
                lastFocusY = detector.focusY
            }
            return true
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val transformationMatrix = Matrix()
            val focusX: Float = detector.focusX
            val focusY: Float = detector.focusY

            with(transformationMatrix) {
                postTranslate(-focusX, -focusY)
                postScale(detector.scaleFactor, detector.scaleFactor)
            }

            val focusShiftX = focusX - lastFocusX
            val focusShiftY = focusY - lastFocusY
            transformationMatrix.postTranslate(focusX + focusShiftX, focusY + focusShiftY)
            drawMatrix.postConcat(transformationMatrix)
            lastFocusX = focusX
            lastFocusY = focusY
            invalidate()
            return true
        }
    }

    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(
            downEvent: MotionEvent,
            currentEvent: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            drawMatrix.postTranslate(-distanceX, -distanceY)
            invalidate()
            return true
        }

        override fun onSingleTapConfirmed(event: MotionEvent): Boolean {
            if (drawMatrix.invert(inverseDrawMatrix)) {
                val point = floatArrayOf(event.x, event.y)
                inverseDrawMatrix.mapPoints(point)
                val (xP, yP) = point
                selectedCoordinates = SelectedCoordinates((xP - centreX) / ratio, (yP - centreY) / ratio)
                onClickImage?.invoke(selectedCoordinates)
                return false
            }
            return true
        }
    }
}