package com.epam.paintwidget

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class PaintWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var seekBarMaxWidth: Int
        get() = with(findViewById<SeekBar>(R.id.widthSeekBar)) {
            max
        }
        set(value) {
            val seekBar = findViewById<SeekBar>(R.id.widthSeekBar)
            seekBar.max = value
        }

    var defaultColorPosition = 0
        set(value) {
            field = value % 4
            val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
            (radioGroup.getChildAt(field) as ColorRadioButton).isChecked = true
        }

    var firstItemColor
        get() = findViewById<ColorRadioButton>(R.id.firstRadioButton).color
        set(value) {
            val colorFromDec = Color.parseColor("#" + Integer.toHexString(value))
            findViewById<ColorRadioButton>(R.id.firstRadioButton).color = colorFromDec
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.paintwidget_view, this, true)

        initCompoundComponents(attrs)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            onRadioGroupClickListener(checkedId)
        }

        val seekBar = findViewById<SeekBar>(R.id.widthSeekBar)
        setSeekBarOnChangeListener(seekBar)
    }

    private fun initCompoundComponents(attrs: AttributeSet? = null) {
        attrs?.let {
            val typedArray = context?.obtainStyledAttributes(it, R.styleable.PaintWidget)
            typedArray?.let {
                seekBarMaxWidth = typedArray.getInteger(R.styleable.PaintWidget_maxWidth, 100)
                defaultColorPosition = typedArray.getInteger(R.styleable.PaintWidget_defaultColorPosition, 0)
                firstItemColor = typedArray.getInt(R.styleable.PaintWidget_firstItemColor, Color.BLACK)
            }
            typedArray?.recycle()
        }
    }

    private fun onRadioGroupClickListener(checkedId: Int) {
        val button = findViewById<ColorRadioButton>(checkedId)
        Toast.makeText(context, "Pressed ${button.hexColor()}", Toast.LENGTH_LONG).show()
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
}