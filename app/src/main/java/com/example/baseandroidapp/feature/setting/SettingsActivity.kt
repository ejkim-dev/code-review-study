package com.example.baseandroidapp.feature.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.baseandroidapp.R
import com.example.baseandroidapp.databinding.ActivitySettingsBinding
import com.example.baseandroidapp.util.base.ViewBindingActivity
import com.example.baseandroidapp.util.preference.PreferenceUtil

class SettingsActivity : ViewBindingActivity<ActivitySettingsBinding>() {


    companion object {
        fun callingIntent(context: Context) = Intent(context, SettingsActivity::class.java)
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivitySettingsBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initGradeInput()

        loadGradeData()

        binding.btnApply.setOnClickListener {
//            Toast.makeText(this, "${binding.picker6.getSettingValue()}", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, getString(R.string.grade_saved_text), Toast.LENGTH_SHORT).show()
            saveGradeData()
        }

    }

    private fun initGradeInput() = with(binding) {
        picker1.setTitle(getString(R.string.grade_1))
        picker2.setTitle(getString(R.string.grade_2))
        picker3.setTitle(getString(R.string.grade_3))
        picker4.setTitle(getString(R.string.grade_4))
        picker5.setTitle(getString(R.string.grade_5))
        picker6.setTitle(getString(R.string.grade_6))
    }

    private fun loadGradeData() = with(binding) {
        val util = PreferenceUtil(this@SettingsActivity)
        picker1.setSettingValue(util.getIntPref(getString(R.string.param_grade_1), 0))
        picker2.setSettingValue(util.getIntPref(getString(R.string.param_grade_2), 0))
        picker3.setSettingValue(util.getIntPref(getString(R.string.param_grade_3), 0))
        picker4.setSettingValue(util.getIntPref(getString(R.string.param_grade_4), 0))
        picker5.setSettingValue(util.getIntPref(getString(R.string.param_grade_5), 0))
        picker6.setSettingValue(util.getIntPref(getString(R.string.param_grade_6), 0))
    }


    private fun saveGradeData() = with(binding) {
        val util = PreferenceUtil(this@SettingsActivity)
        util.setIntPref(getString(R.string.param_grade_1), picker1.getSettingValue())
        util.setIntPref(getString(R.string.param_grade_2), picker2.getSettingValue())
        util.setIntPref(getString(R.string.param_grade_3), picker3.getSettingValue())
        util.setIntPref(getString(R.string.param_grade_4), picker4.getSettingValue())
        util.setIntPref(getString(R.string.param_grade_5), picker5.getSettingValue())
        util.setIntPref(getString(R.string.param_grade_6), picker6.getSettingValue())
    }

}