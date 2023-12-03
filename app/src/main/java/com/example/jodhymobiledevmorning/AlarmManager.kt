package com.example.jodhymobiledevmorning

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.jodhymobiledevmorning.databinding.FragmentAlarmManagerBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AlarmManager : Fragment(), DatePickerDialog.OnDateSetListener {
    private var _binding: FragmentAlarmManagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlarmManagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.setReminder.setOnClickListener {
            showDatePicker()
        }
    }

    private fun updateSelectedDateTime(dateTime: String) {
        binding.tvSelectedDate.text = dateTime
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val selectedDate = Calendar.getInstance()
        selectedDate.set(year, month, dayOfMonth)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedDate = dateFormat.format(selectedDate.time)
        updateSelectedDateTime(formattedDate)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
