package com.epam.paintwidget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Activity which contains [PaintWidget].
 *
 * @author Vlad Korotkevich
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        closeButton.setOnClickListener {
            paintWidget.isVisible = !paintWidget.isVisible
        }
    }
}
