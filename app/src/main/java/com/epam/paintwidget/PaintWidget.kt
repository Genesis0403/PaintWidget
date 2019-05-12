package com.epam.paintwidget

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.ColorUtils

/**
 * Custom view which contains [SeekBar] and [RadioGroup] of [ColorRadioButton].
 *
 * @author Vlad Korotkevich
 */

class PaintWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val seekBar by lazy { findViewById<SeekBar>(R.id.widthSeekBar) }

    init {
        inflate(context, R.layout.paintwidget_view, this)

        initCompoundComponents(attrs)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            onRadioGroupClickListener(checkedId)
        }

        setSeekBarOnChangeListener(seekBar)
    }

    private fun initCompoundComponents(attrs: AttributeSet? = null) {
        attrs?.let {
            val typedArray = context?.obtainStyledAttributes(it, R.styleable.PaintWidget)
            typedArray?.let {
                seekBar.max = typedArray.getInteger(R.styleable.PaintWidget_maxWidth, 100)
                changeSeekBarColor(typedArray.getInt(R.styleable.PaintWidget_firstItemColor, Color.BLACK))
            }
            typedArray?.recycle()
        }
    }

    private fun onRadioGroupClickListener(checkedId: Int) {
        val button = findViewById<ColorRadioButton>(checkedId)
        changeSeekBarColor(button.color)
        Toast.makeText(context, "Pressed #${Integer.toHexString(button.color)}", Toast.LENGTH_LONG).show()
    }

    private fun changeSeekBarColor(color: Int) {
        seekBar.progressTintList = ColorStateList.valueOf(color)
        seekBar.progressBackgroundTintList = ColorStateList.valueOf(ColorUtils.setAlphaComponent(color, ALPHA))
    }

    private fun setSeekBarOnChangeListener(seekBar: SeekBar) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val seekBarWidthText = findViewById<TextView>(R.id.widthNumber)
                seekBarWidthText.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private companion object {
        private const val ALPHA = 150
    }
}