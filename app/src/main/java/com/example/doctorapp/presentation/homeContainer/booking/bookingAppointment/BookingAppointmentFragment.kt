package com.example.doctorapp.presentation.homeContainer.booking.bookingAppointment

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.doctorapp.R
import com.example.doctorapp.databinding.FragmentBookingAppointmentBinding
import com.example.doctorapp.domain.core.base.BaseFragment
import com.example.doctorapp.presentation.utils.DateUtils
import com.example.doctorapp.presentation.utils.DateTimePickerDialog
import java.util.Calendar

class BookingAppointmentFragment : BaseFragment<FragmentBookingAppointmentBinding, BookingAppointmentViewModel>(R.layout.fragment_booking_appointment), DateTimePickerDialog.OnDateTimePickerListener {

    companion object {
        fun newInstance() = BookingAppointmentFragment()
    }

    private val viewModel: BookingAppointmentViewModel by viewModels()
    override fun getVM() = viewModel
    private val fullSlotDay = mutableListOf<Long>(1728950400000, 1729209600000, 1729382400000, 1729468800000)
    override fun setOnClick() {
        super.setOnClick()
        binding.tvDate.setOnClickListener {
            DateTimePickerDialog.getInstance(fullSlotDay, this).showDatePickerDialog(childFragmentManager)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        binding.apply {
            tvDate.text = DateUtils.convertLongToDate(Calendar.getInstance().timeInMillis)
        }
    }

    override fun onDateTimeSelected(date: Long) {
        binding.tvDate.text = DateUtils.convertLongToDate(date)
    }

}