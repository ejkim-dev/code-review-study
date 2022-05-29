package com.example.baseandroidapp.feature.insert

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.example.baseandroidapp.AndroidApplication.Companion.DAY_COUNT
import com.example.baseandroidapp.AndroidApplication.Companion.GYOSI_COUNT
import com.example.baseandroidapp.R
import com.example.baseandroidapp.databinding.ActivityInsertTableBinding
import com.example.baseandroidapp.feature.grid.VerticalLayout
import com.example.baseandroidapp.feature.grid.filter.FilterView
import com.example.baseandroidapp.feature.main.ScheduleItem
import com.example.baseandroidapp.feature.main.ScheduleView
import com.example.baseandroidapp.util.base.ViewBindingActivity
import com.example.baseandroidapp.util.extension.toDayText
import com.example.baseandroidapp.util.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class InsertTableActivity : ViewBindingActivity<ActivityInsertTableBinding>() {

    @Inject
    lateinit var navigator: Navigator
    private val viewModel: InsertTableViewModel by viewModels()

    private val layoutMap: MutableMap<String, VerticalLayout> = mutableMapOf()

    companion object {
        fun callingIntent(context: Context) = Intent(context, InsertTableActivity::class.java)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityInsertTableBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTable()
        initObserver()

        with(binding) {
            btnClear.setOnClickListener {
                clearTable()
                clearSelectedTable()
            }
        }
    }

    private fun initObserver() {


    }

    private fun initTable() {
        for (i in 1..GYOSI_COUNT) {
            for (j in 1..DAY_COUNT) {
                val layout = VerticalLayout(this)
                binding.layoutGrid.addView(
                    layout, GridLayout.LayoutParams(
                        GridLayout.spec(i, GridLayout.CENTER),
                        GridLayout.spec(j, GridLayout.CENTER)
                    ).apply {
                        setGravity(Gravity.FILL)
                    }
                )
                layoutMap["${i}-${j}"] = layout
                layout.setOnClickListener {
                    clickTimeTable(layout)
                    Toast.makeText(this, "${i}교시 - ${j.toDayText()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun clearSelectedTable() {
        for (i in 1..GYOSI_COUNT) {
            for (j in 1..DAY_COUNT) {
                layoutMap["${i}-${j}"]?.background =
                    ContextCompat.getDrawable(
                        this@InsertTableActivity,
                        R.drawable.bg_vertical_layout
                    )
            }
        }
    }

    private fun clickTimeTable(layout: VerticalLayout) {
        clearSelectedTable()
        layout.background = ContextCompat.getDrawable(
            this@InsertTableActivity,
            R.drawable.bg_vertical_layout_selected
        )
    }


    private fun addItem(scheduleView: ScheduleView) {
        val item = ScheduleItem(
            this,
            scheduleView
        )
        layoutMap["${scheduleView.gyosi}-${scheduleView.weekDay - 1}"]?.addView(item)
        item.setOnClickListener {
            layoutMap["${scheduleView.gyosi}-${scheduleView.weekDay - 1}"]?.callOnClick()
        }
    }

    private fun clearTable() {
        for (i in 1..GYOSI_COUNT) {
            for (j in 1..DAY_COUNT) {
                layoutMap["${i}-${j}"]?.removeAllViews()
            }
        }
    }

}