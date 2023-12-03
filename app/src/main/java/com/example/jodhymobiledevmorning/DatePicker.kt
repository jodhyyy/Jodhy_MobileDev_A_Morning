package com.example.jodhymobiledevmorning

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class DateTimePicker : DialogFragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener{

    private val calendar = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(requireActivity(), this, hour, minute, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val selectedDateTime = calendar.timeInMillis

        startTimer(selectedDateTime)

        val formattedDateTime =
            SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ENGLISH).format(calendar.time)
        val selectedDateTimeBundle = Bundle()
        selectedDateTimeBundle.putString("SELECTED_DATE_TIME", formattedDateTime)

        parentFragmentManager.setFragmentResult("REQUEST_KEY", selectedDateTimeBundle)
    }

    private fun startTimer(selectedDateTime: Long) {
        Timer().schedule(object : TimerTask() {
            override fun run() {

            }
        }, selectedDateTime - System.currentTimeMillis())
    }
}