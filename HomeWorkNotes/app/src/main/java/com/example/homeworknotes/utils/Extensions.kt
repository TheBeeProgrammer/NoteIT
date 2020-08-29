package com.example.homeworknotes.utils

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

fun TextView.setCurrentDate() {
    val calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("dd MMMM yyyy")
    this.text = formatter.format(calendar.time)
}

fun TextView.setFormatDate(date: Date) {
    val formatter = SimpleDateFormat("dd MMMM yyyy")
    this.text = formatter.format(date)
}