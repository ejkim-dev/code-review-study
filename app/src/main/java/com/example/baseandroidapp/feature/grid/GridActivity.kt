package com.example.baseandroidapp.feature.grid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.GridLayout
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidapp.databinding.ActivityGridBinding
import com.example.baseandroidapp.feature.grid.filter.FilterAdapter
import com.example.baseandroidapp.feature.grid.filter.FilterView
import com.example.baseandroidapp.feature.main.ScheduleItem
import com.example.baseandroidapp.feature.main.ScheduleView
import com.example.baseandroidapp.util.base.ViewBindingActivity
import com.example.baseandroidapp.util.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class GridActivity : ViewBindingActivity<ActivityGridBinding>() {

    @Inject
    lateinit var navigator: Navigator

    private val viewModel: GridViewModel by viewModels()

    private val GYOSI_COUNT = 6
    private val DAY_COUNT = 5
    private val BAN_COUNT = 10

    val list = listOf(
        ScheduleView(grade = 1, ban = 1, gyosi = 2, week = "Mon", weekDay = Calendar.MONDAY),
        ScheduleView(grade = 2, ban = 2, gyosi = 1, week = "Wed", weekDay = Calendar.WEDNESDAY),
        ScheduleView(grade = 3, ban = 1, gyosi = 2, week = "Thu", weekDay = Calendar.THURSDAY),
        ScheduleView(grade = 4, ban = 10, gyosi = 5, week = "Tue", weekDay = Calendar.TUESDAY),
        ScheduleView(grade = 2, ban = 1, gyosi = 2, week = "Fri", weekDay = Calendar.FRIDAY),
        ScheduleView(grade = 4, ban = 3, gyosi = 3, week = "Mon", weekDay = Calendar.MONDAY),
        ScheduleView(grade = 5, ban = 1, gyosi = 4, week = "Mon", weekDay = Calendar.MONDAY),
        ScheduleView(grade = 6, ban = 2, gyosi = 5, week = "Mon", weekDay = Calendar.MONDAY),
        ScheduleView(grade = 2, ban = 6, gyosi = 3, week = "Mon", weekDay = Calendar.FRIDAY),
        ScheduleView(grade = 5, ban = 4, gyosi = 4, week = "Mon", weekDay = Calendar.TUESDAY),
        ScheduleView(grade = 5, ban = 1, gyosi = 6, week = "Mon", weekDay = Calendar.WEDNESDAY),
    )

    private var gradeList = mutableListOf<Boolean>()
    private var banList = listOf<FilterView>()

    private val layoutMap: MutableMap<String, VerticalLayout> = mutableMapOf()

    companion object {
        fun callingIntent(context: Context) = Intent(context, GridActivity::class.java)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityGridBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initGradeFilter()
        initBanFilter()
        initTable()

        viewModel.setGradeCheckList(mutableListOf(false, false, false, false, false, false))
        initObserver()

        with(binding) {
//            btnAdd.setOnClickListener {
////            addItem(binding.etRow.text.toString().toInt(), binding.etColumn.text.toString().toInt())
//                list.forEach {
//                    addItem(it)
//                }
//            }

//            btnClear.setOnClickListener {
//                clearTable()
//            }

            btnSetting.setOnClickListener {
                navigator.showSetting(this@GridActivity)
            }
        }
    }

    private fun initObserver() {

        viewModel.gradeCheckList.observe(this) {
            with(binding.gradeFilter) {
                cbGrade1.isChecked = it[0]
                cbGrade2.isChecked = it[1]
                cbGrade3.isChecked = it[2]
                cbGrade4.isChecked = it[3]
                cbGrade5.isChecked = it[4]
                cbGrade6.isChecked = it[5]
                cbAll.isChecked = (it[0] && it[1] && it[2] && it[3] && it[4] && it[5])

                clearTable()
                gradeList = it
                addItem()
            }
        }

        viewModel.banCheckList.observe(this){
            banList = it.map { it.copy() }
            clearTable()
            addItem()
        }

    }


    private fun initGradeFilter() = with(binding.gradeFilter) {
        cbAll.setOnClickListener {
            val b = cbAll.isChecked
            val array = mutableListOf(b, b, b, b, b, b)
            viewModel.setGradeCheckList(array)
        }
        cbGrade1.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.setGradeIndex(0, b)
        }

        cbGrade2.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.setGradeIndex(1, b)
        }

        cbGrade3.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.setGradeIndex(2, b)
        }

        cbGrade4.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.setGradeIndex(3, b)
        }

        cbGrade5.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.setGradeIndex(4, b)
        }

        cbGrade6.setOnCheckedChangeListener { compoundButton, b ->
            viewModel.setGradeIndex(5, b)
        }
    }

    private fun initBanFilter() = with(binding.banFilter) {

        val filterAdapter = FilterAdapter().apply {
            itemClickListener = {
                var allChecked = true
                for (i in filterViewList) {
                    if (!i.isChecked) {
                        allChecked = false
                        break
                    }
                }
                cbAll.isChecked = allChecked
                viewModel.setBanCheckList(filterViewList)
            }
        }

        cbAll.setOnClickListener {
            val b = cbAll.isChecked
            val list = mutableListOf<FilterView>()
            for (i in 1..BAN_COUNT) {
                list.add(FilterView(name = "${i}반", value = i, isChecked = b))
            }
            filterAdapter.addList(list)
            viewModel.setBanCheckList(list)
        }


        rvFilter.adapter = filterAdapter
        rvFilter.layoutManager = LinearLayoutManager(this@GridActivity).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }

        val list = mutableListOf<FilterView>()
        for (i in 1..BAN_COUNT) {
            list.add(FilterView(name = "${i}반", value = i))
        }
        filterAdapter.addList(list)
        viewModel.setBanCheckList(list)
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
            }
        }
    }

    private fun addItem() {
        list.forEach { scheduleView ->
            if (gradeList[scheduleView.grade - 1]) {
                banList.forEach {
                    if(it.value == scheduleView.ban && it.isChecked){
                        addItem(scheduleView)
                    }
                }
            }
        }
    }

    private fun addItem(row: Int, column: Int) {
        val item = ScheduleItem(
            this,
            ScheduleView(
                name = "도덕(곰)",
                grade = row,
                ban = column,
                gyosi = 2,
                week = "Tue",
                weekDay = Calendar.MONDAY
            )
        )
        layoutMap["$row-$column"]?.addView(item)

    }

    private fun addItem(scheduleView: ScheduleView) {
        val item = ScheduleItem(
            this,
            scheduleView
        )
        layoutMap["${scheduleView.gyosi}-${scheduleView.weekDay - 1}"]?.addView(item)
    }

    private fun clearTable() {
        for (i in 1..GYOSI_COUNT) {
            for (j in 1..DAY_COUNT) {
                layoutMap["${i}-${j}"]?.removeAllViews()
            }
        }
    }
}