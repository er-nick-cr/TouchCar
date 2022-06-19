package com.example.feature_parts.component

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.*
import com.example.core_common.SelectedCoordinates
import com.example.core_data.domain.entity.ComponentImageSize
import com.example.core_data.domain.entity.Coordinates

class ComponentImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyle) {

    lateinit var items: List<Coordinates>
    lateinit var image: Bitmap
    lateinit var onClickImage: (SelectedCoordinates) -> Unit
    private val paint = Paint()
    var lastFocusX = 0f
    var lastFocusY = 0f
    var selectedCoordinates = SelectedCoordinates(0f, 0f)
    var drawMatrix = Matrix()
    var inverseDrawMatrix = Matrix()
    var centreX = 0f
    var centreY = 0f

    private val scaleListener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            if (detector != null) {
                lastFocusX = detector.focusX
            };
            if (detector != null) {
                lastFocusY = detector.focusY
            };
            return true
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val transformationMatrix = Matrix()
            val focusX: Float = detector.focusX
            val focusY: Float = detector.focusY
            transformationMatrix.postTranslate(-focusX, -focusY)
            transformationMatrix.postScale(detector.scaleFactor, detector.scaleFactor)

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

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(
            downEvent: MotionEvent?,
            currentEvent: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            drawMatrix.postTranslate(-distanceX, -distanceY)
            invalidate()
            return true
        }

        override fun onSingleTapConfirmed(event: MotionEvent?): Boolean {
            if (event != null) {
                if(drawMatrix.invert(inverseDrawMatrix)) {
                    val point = floatArrayOf(event.x, event.y)
                    inverseDrawMatrix.mapPoints(point)
                    val (xP, yP) = point
                    selectedCoordinates = SelectedCoordinates(xP - centreX, yP - centreY)
                    Log.d("selctcoords", selectedCoordinates.x.toString() + " " + selectedCoordinates.y.toString())
                    onClickImage.invoke(selectedCoordinates)
                }

            }
            return true
        }
    }


    private val scaleDetector = ScaleGestureDetector(context, scaleListener)
    private val gestureDetector = GestureDetector(context, gestureListener)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun onDragEvent(event: DragEvent?): Boolean {
        return super.onDragEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            paint.color = Color.BLUE
            paint.strokeWidth = 5F
            paint.style = Paint.Style.STROKE
            if (::image.isInitialized) {
                centreX = ((width - image.width)/2).toFloat()
                centreY = ((height - image.height)/2).toFloat()
                setMatrix(drawMatrix)
                translate(centreX, centreY)
                drawBitmap(image, 0F, 0F, null)
                if(::items.isInitialized) {
                    items.map { item ->
                        canvas.drawRect(item.x1, item.y1, item.x2, item.y2, paint)
                    }
                }
            }
        }
        invalidate()
    }
}