package com.epam.paintwidget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton

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
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ColorRadioButton)
            color = typedArray.getColor(R.styleable.ColorRadioButton_color, Color.BLACK)
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = (MeasureSpec.getSize(widthMeasureSpec))
        val height = (MeasureSpec.getSize(heightMeasureSpec))
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        paint.style = Paint.Style.FILL
        if (isChecked) {
            paint.color = strokeColor
            canvas?.drawCircle(w/2, h/2, w/2, paint)
            paint.color = color
            canvas?.drawCircle(w/2, h/2, w/3, paint)
        } else {
            paint.color = color
            canvas?.drawCircle(w/2, h/2, w/2, paint)
        }
    }
}
