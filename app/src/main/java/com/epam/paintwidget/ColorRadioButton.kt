package com.epam.paintwidget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.graphics.ColorUtils

/**
 * Radiobutton which contains its color.
 *
 * @author Vlad Korotkevich
 */

class ColorRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatRadioButton(context, attrs) {

    var color = Color.BLACK
    private val alphaColor
        get() = ColorUtils.setAlphaComponent(color, ALPHA)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ColorRadioButton)
            color = typedArray.getColor(R.styleable.ColorRadioButton_color, Color.BLACK)
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = (SCALE_FACTOR * MeasureSpec.getSize(widthMeasureSpec)).toInt()
        val height = (SCALE_FACTOR * MeasureSpec.getSize(heightMeasureSpec)).toInt()
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        val w = width.toFloat()
        val h = height.toFloat()
        paint.style = Paint.Style.FILL
        if (isChecked) {
            paint.color = alphaColor
            canvas?.drawCircle(w / 2, h / 2, w / 2, paint)
            paint.color = color
            canvas?.drawCircle(w / 2, h / 2, w / 3, paint)
        } else {
            paint.color = color
            canvas?.drawCircle(w / 2, h / 2, w / 2, paint)
        }
    }

    private companion object {
        private const val SCALE_FACTOR = 1.5
        private const val ALPHA = 90
    }
}
