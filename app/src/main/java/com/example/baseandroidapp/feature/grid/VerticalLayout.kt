package com.example.baseandroidapp.feature.grid

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.core.content.ContextCompat
import com.example.baseandroidapp.R
import com.example.baseandroidapp.databinding.ItemLayoutVerticalBinding

class VerticalLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

//    val binding = ItemLayoutVerticalBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        orientation = VERTICAL
        background = ContextCompat.getDrawable(context, R.drawable.bg_vertical_layout)
        layoutParams = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        ).apply {
//            setMargins(
//                10,10,10,10
//            )
        }
        minimumWidth = 150
        minimumHeight = 100
    }
}