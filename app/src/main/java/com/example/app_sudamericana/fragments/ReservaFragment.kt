package com.example.app_sudamericana.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.app_sudamericana.R
import com.example.app_sudamericana.databinding.FragmentReservaBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat


class ReservaFragment : Fragment() {

    private  var _binding: FragmentReservaBinding? = null
    private val binding get() = _binding!!
    private  lateinit var datePicker: MaterialTimePicker


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReservaBinding.inflate(inflater, container, false)
        return binding.root

    }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            _binding = FragmentReservaBinding.bind(view)

            //Funcion Hora y fecha
            binding.apply {
                //Funcion Hora
                btnSelectDate.setOnClickListener {
                    // crear una nueva instancia de DatePickerFragment
                    val datePickerFragment = DatePickerFragment()
                    val supportFragmentManager = requireActivity().supportFragmentManager
                    //tenemos que implementar setFragmentResultListener
                    supportFragmentManager.setFragmentResultListener(
                        "REQUEST_KEY", viewLifecycleOwner
                    ) { resultKey, bundle ->
                        if (resultKey == "REQUEST_KEY") {
                            val date = bundle.getString("SELECTED_DATE")
                            tvSelectedDate.text = date
                        }
                    }

                    // show
                    datePickerFragment.show(supportFragmentManager, "DatePickerFragment")
                }

                //Funcion Hora
                BtnSelectHora.setOnClickListener {
                    datePicker.show(childFragmentManager, "tag");
                }
                datePicker =
                    MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(10)
                        .build()
                timePickerCallback()
            }
        }


    //Funcion Hora
    fun timePickerCallback(){
        datePicker?.let{mDatePicker ->
            mDatePicker.addOnPositiveButtonClickListener {
                Log.wtf("timePicker1",mDatePicker.hour.toString())
                var format=if(mDatePicker.hour>=13){
                    "PM"
                }else{
                    "AM"
                }
                binding.TxtHora.text="${mDatePicker.hour} : ${mDatePicker.minute} $format"
            }
            mDatePicker.addOnNegativeButtonClickListener {
                Log.wtf("timePicker2",mDatePicker.hour.toString())
            }
            mDatePicker.addOnCancelListener {
                Log.wtf("timePicker3",mDatePicker.hour.toString())
            }
            mDatePicker.addOnDismissListener {
                Log.wtf("timePicker4",mDatePicker.hour.toString())
            }
        }

    }

    override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }







    }