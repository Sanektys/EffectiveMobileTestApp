package com.example.effectivemobiletestapp.ui.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker


object DatePickerDialogBuilder {

    fun buildAndShow(
        context: Context,
        fragmentManager: FragmentManager,
        @StringRes datePickerTitle: Int?,
        onDateTimeSet: (date: Long) -> Unit
    ) {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText(if (datePickerTitle != null) context.getString(datePickerTitle) else null)
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())
                    .build()
            )
            .build()
            .apply {
                addOnPositiveButtonClickListener { date ->
                    onDateTimeSet.invoke(date)
                }
            }
            .show(fragmentManager, TAG)
    }

    const val TAG = "DatePickerDialog"
}