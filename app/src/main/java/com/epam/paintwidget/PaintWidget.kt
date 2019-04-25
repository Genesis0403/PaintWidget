package com.epam.paintwidget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

class PaintWidget @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.paintwidget_view, this, true)

        val seekBar = findViewById<SeekBar>(R.id.widthSeekBar)
        seekBar.progress = 0
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        attrs?.let {
           val typedArray = context?.obtainStyledAttributes(it, R.styleable.PaintWidget)
            typedArray?.let {
                seekBar.max = typedArray.getInteger(R.styleable.PaintWidget_maxWidth, 100)
                val defaultColorPosition = typedArray.getInteger(R.styleable.PaintWidget_defaultColorPosition, 0) % 4
                (radioGroup.getChildAt(defaultColorPosition) as ColorRadioButton).isChecked = true
            }
            typedArray?.recycle()
        }

        //TODO fix radio group onCheckedListener
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            Toast.makeText(context, "Pressed $checkedId", Toast.LENGTH_LONG).show()
        }

        setSeekBarOnChangeListener(seekBar)
    }

    private fun setSeekBarOnChangeListener(seekBar: SeekBar) {
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val seekBarWidthText = findViewById<TextView>(R.id.widthNumber)
                seekBarWidthText.text = progress.toString()
                findViewById<ColorRadioButton>(R.id.firstRadioButton).isChecked = true
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}