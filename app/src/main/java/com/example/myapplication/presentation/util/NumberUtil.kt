
package com.example.myapplication.presentation.util

fun Long.toPersianNumber(): String {
    val persian = arrayOf(
        '۰','۱','۲','۳','۴',
        '۵','۶','۷','۸','۹'
    )

    return this.toString().map {
        if(it.isDigit()){
            persian[it.digitToInt()]
        }else{
            it
        }
    }.joinToString("")
}


fun Int.toPersianNumber(): String {
    return this.toLong().toPersianNumber()
}