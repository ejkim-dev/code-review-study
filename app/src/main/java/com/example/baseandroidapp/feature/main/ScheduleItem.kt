package com.example.baseandroidapp.feature.main

import android.content.Context
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.example.baseandroidapp.R

class ScheduleItem(context: Context, viewData:ScheduleView): LinearLayout(context) {


    init{
        inflate(context, R.layout.item_schedule, this)

        val nameTextView = findViewById<TextView>(R.id.tv_name)
        val classTextView = findViewById<TextView>(R.id.tv_class)

        nameTextView.text = viewData.name
        classTextView.text = "${viewData.grade}-${viewData.ban}"

        rootView.layoutParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        )
        gravity = Gravity.CENTER
        setBackgroundColor(ContextCompat.getColor(context, R.color.white))

        this.setOnClickListener {
            Toast.makeText(context, "${viewData.grade}-${viewData.ban}", Toast.LENGTH_SHORT).show()
        }
    }


}