package com.devhoony.baseandroidapp

import com.devhoony.baseandroidapp.util.DLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun flowTest() {
        val numberFlow: Flow<Int> = flow {
//            repeat(3){
                delay(1000)
//                emit(it)
//            }
        }

        val stringFlow : Flow<String> = numberFlow.map {
            delay(1000)
            (it + 1).toString()
        }

        runBlocking {
            numberFlow.collect{
                println(it)
            }
            stringFlow.collect{
                println(it)
            }
        }


    }
}