package com.epam.paintwidget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.res.use

class ColorRadioButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatRadioButton(context, attrs, defStyleAttr) {

    var color = Color.BLACK
    var strokeColor = Color.parseColor("#7E7E7F")

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.ColorRadioButton).use {typedArray ->
                color = typedArray.getColor(R.styleable.ColorRadioButton_color, Color.BLACK)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        if (isChecked) {
            paint.style = Paint.Style.STROKE
            canvas?.drawColor(strokeColor)
            canvas?.drawCircle(w/2, h/2, w/2, paint)
            canvas?.drawColor(color)
            paint.style = Paint.Style.FILL
            canvas?.drawCircle(w/3, h/3, w/3, paint)
        } else {
            paint.style = Paint.Style.FILL
            canvas?.drawColor(color)
            canvas?.drawCircle(w/2, h/2, w/2, paint)
        }
    }
}