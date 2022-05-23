package com.example.baseandroidapp.feature.main

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.baseandroidapp.R
import com.example.baseandroidapp.databinding.ItemRowDayBinding
import com.example.baseandroidapp.databinding.ItemRowGyosiBinding

class GyosiRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : TableRow(context, attrs) {

    val binding = ItemRowGyosiBinding.inflate(LayoutInflater.from(context), this, true)

    init {
//        inflate(context, R.layout.item_row_day, this)
        gravity = Gravity.CENTER
    }

}