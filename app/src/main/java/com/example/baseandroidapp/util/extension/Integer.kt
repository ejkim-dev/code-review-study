package com.example.baseandroidapp.util.extension

import java.util.*


fun Int.toDayText():String{
    return when(this + 1){
        Calendar.MONDAY-> "월"
        Calendar.TUESDAY-> "화"
        Calendar.WEDNESDAY-> "수"
        Calendar.THURSDAY-> "목"
        Calendar.FRIDAY-> "금"
        else -> "없음"
    }
}