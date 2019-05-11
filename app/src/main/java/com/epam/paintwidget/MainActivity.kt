package com.epam.paintwidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        closeButton.setOnClickListener {
            onClickListener()
        }
    }

    private fun onClickListener() {
        paintWidget.isVisible = !paintWidget.isVisible
    }
}
