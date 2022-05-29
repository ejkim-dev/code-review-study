package com.example.baseandroidapp.feature.setting

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import com.example.baseandroidapp.R
import com.example.baseandroidapp.databinding.ItemSettingsPickerBinding

class SettingsPicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    val binding = ItemSettingsPickerBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        orientation = HORIZONTAL
        binding.gradePicker.minValue = 0
        binding.gradePicker.maxValue = 20

        val items = resources.getStringArray(R.array.grade_ban_array)
        val arrayAdapter =
            ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, items)
        binding.gradeSpinner.adapter = arrayAdapter
    }

    fun setMinValue(value: Int) {
        binding.gradePicker.minValue = value
    }

    fun setMaxValue(value: Int) {
        binding.gradePicker.maxValue = value
    }


    //    fun getSettingValue(): Int = binding.gradeSpinner.selectedItemPosition
    fun getSettingValue(): Int = binding.gradePicker.value

    fun setSettingValue(value: Int) {
        binding.gradeSpinner.setSelection(value)
        binding.gradePicker.value = value
    }
}