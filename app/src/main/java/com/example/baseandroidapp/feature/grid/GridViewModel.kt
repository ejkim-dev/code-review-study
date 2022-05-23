package com.example.baseandroidapp.feature.grid

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.baseandroidapp.feature.grid.filter.FilterView
import com.example.baseandroidapp.util.base.BaseViewModel

class GridViewModel : BaseViewModel() {

    private val _gradeCheckList: MutableLiveData<MutableList<Boolean>> = MutableLiveData()
    val gradeCheckList: LiveData<MutableList<Boolean>> = _gradeCheckList

    fun setGradeCheckList(list : MutableList<Boolean>){
        _gradeCheckList.value = list
    }

    fun setGradeIndex(index: Int, value: Boolean){
        gradeCheckList.value?.let{
            it[index] = value
            setGradeCheckList(it)
        }
    }

    private val _banCheckList: MutableLiveData<MutableList<FilterView>> = MutableLiveData()
    val banCheckList: LiveData<MutableList<FilterView>> = _banCheckList

    fun setBanCheckList(list : MutableList<FilterView>){
        _banCheckList.value = list
    }



}