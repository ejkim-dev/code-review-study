package com.example.baseandroidapp.feature.main


/**
 * @param name 과목 이름
 * @param grade 학년
 * @param ban 반
 * @param week  수업 요일
 * @param gyosi 수업의 n교시
 * */
data class ScheduleView(
    val name: String = "도덕",
    val grade : Int = 0,
    val ban: Int = 10,
    val week: String = "",
    val weekDay: Int = 0,
    val gyosi: Int = 0, // 1,2,3,4,5,6
)