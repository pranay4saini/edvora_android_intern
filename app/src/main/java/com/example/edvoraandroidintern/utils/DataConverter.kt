package com.example.edvoraandroidintern.utils

import java.text.SimpleDateFormat

fun stringToTimeStamp(stringDate: String): Long {
    val format = SimpleDateFormat("MM/dd/yyyy hh:mm a")
    val date = format.parse(stringDate)
    return date?.time ?: 0
}