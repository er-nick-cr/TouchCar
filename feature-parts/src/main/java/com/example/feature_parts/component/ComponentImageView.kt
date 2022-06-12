package com.example.feature_parts.component

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.MotionEvent.INVALID_POINTER_ID
import android.view.ScaleGestureDetector
import androidx.core.graphics.createBitmap
import androidx.core.view.MotionEventCompat
import androidx.core.view.ViewCompat
import com.example.core_data.domain.entity.ComponentImageSize
import com.example.core_data.domain.entity.Coordinates
import java.io.File
import android.view.MotionEvent.INVALID_POINTER_ID


class ComponentImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyle) {

    lateinit var items: List<Coordinates>
    lateinit var image: Bitmap
    lateinit var imageSize: ComponentImageSize
    private val paint = Paint()
    var lastFocusX = 0f
    var lastFocusY = 0f
    var drawMatrix = Matrix()
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
            paint.strokeWidth = 10F
            paint.style = Paint.Style.STROKE
            if (::image.isInitialized) {
                val centreX = (width - image.width)
                val centreY = (height - image.height)
                setMatrix(drawMatrix)
                translate(centreX.toFloat() / 2, centreY.toFloat() / 2)
                drawBitmap(image, 0F, 0F, null)
                items.map { item ->
                    canvas.drawRect(item.x1, item.y1, item.x2, item.y2, paint)
                }
            }
        }
        invalidate()
    }
}