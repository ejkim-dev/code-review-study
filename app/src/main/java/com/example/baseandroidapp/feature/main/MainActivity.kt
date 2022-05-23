package com.example.baseandroidapp.feature.main

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.baseandroidapp.databinding.ActivityMainBinding
import com.example.baseandroidapp.databinding.ItemRowDayBinding
import com.example.baseandroidapp.util.base.ViewBindingActivity

class MainActivity : ViewBindingActivity<ActivityMainBinding>() {


    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initGyosi()

        with(binding) {
            btnClear.setOnClickListener {
                clearGyosi()
            }

            btnAdd.setOnClickListener {
                addItems()
            }
        }

    }


    val list = listOf(
        ScheduleView(grade = 0, ban = 1, gyosi = 2, week = "Mon"),
        ScheduleView(grade = 0, ban = 1, gyosi = 2, week = "Wed"),
        ScheduleView(grade = 0, ban = 1, gyosi = 2, week = "Thu"),
        ScheduleView(name="도덕(곰)",grade = 0, ban = 10, gyosi = 2, week = "Tue"),
        ScheduleView(grade = 0, ban = 1, gyosi = 2, week = "Fri"),
        ScheduleView(grade = 0, ban = 1, gyosi = 3, week = "Mon"),
        ScheduleView(grade = 0, ban = 1, gyosi = 4, week = "Mon"),
    )

    private fun initGyosi() = with(binding) {
        gyosi1.binding.tvNum.text = "1"
        gyosi2.binding.tvNum.text = "2"
        gyosi3.binding.tvNum.text = "3"
        gyosi4.binding.tvNum.text = "4"
        gyosi5.binding.tvNum.text = "5"
        gyosi6.binding.tvNum.text = "6"
    }


    private fun addItems() {
        list.forEach {
            checkGyosi(it)
        }
    }

    private fun addItems(layout: LinearLayout, data: ScheduleView) {
        val item = ScheduleItem(this, data)
        layout.addView(item)
    }

    private fun checkGyosi(data: ScheduleView) {
        when (data.gyosi) {
            1 -> checkWeek(binding.gyosi1, data)
            2 -> checkWeek(binding.gyosi2, data)
            3 -> checkWeek(binding.gyosi3, data)
            4 -> checkWeek(binding.gyosi4, data)
            5 -> checkWeek(binding.gyosi5, data)
            6 -> checkWeek(binding.gyosi6, data)
        }
    }

    private fun checkWeek(week: GyosiRow, data: ScheduleView) {
        with(week.binding) {
            when (data.week) {
                "Mon" -> addItems(layoutMon, data)
                "Tue" -> addItems(layoutTue, data)
                "Wed" -> addItems(layoutWed, data)
                "Thu" -> addItems(layoutThu, data)
                "Fri" -> addItems(layoutFri, data)
            }
        }
    }

    private fun checkWeek(week: ItemRowDayBinding, data: ScheduleView) {
        when (data.week) {
            "Mon" -> addItems(week.layoutMon, data)
            "Tue" -> addItems(week.layoutTue, data)
            "Wed" -> addItems(week.layoutWed, data)
            "Thu" -> addItems(week.layoutThu, data)
            "Fri" -> addItems(week.layoutFri, data)
        }
    }

    private fun clearGyosi() {
        clearWeek(binding.gyosi1)
        clearWeek(binding.gyosi2)
        clearWeek(binding.gyosi3)
        clearWeek(binding.gyosi4)
        clearWeek(binding.gyosi5)
        clearWeek(binding.gyosi6)
    }

    private fun clearWeek(week: GyosiRow) {
        with(week.binding) {
            layoutMon.removeAllViews()
            layoutTue.removeAllViews()
            layoutWed.removeAllViews()
            layoutThu.removeAllViews()
            layoutFri.removeAllViews()
        }
    }

    private fun clearWeek(week: ItemRowDayBinding) {
        week.layoutMon.removeAllViews()
        week.layoutTue.removeAllViews()
        week.layoutWed.removeAllViews()
        week.layoutThu.removeAllViews()
        week.layoutFri.removeAllViews()
    }


}