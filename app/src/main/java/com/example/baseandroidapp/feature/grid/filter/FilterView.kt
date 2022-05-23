package com.example.baseandroidapp.feature.grid.filter

data class FilterView(
    val name: String,
    val value: Int,
    val isChecked: Boolean = false
)