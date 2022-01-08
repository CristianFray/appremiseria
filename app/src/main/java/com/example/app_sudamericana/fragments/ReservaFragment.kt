package com.example.app_sudamericana.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.app_sudamericana.API.Domain.RegisterReservation
import com.example.app_sudamericana.API.Domain.Response.RegisterReservationResponse
import com.example.app_sudamericana.API.Domain.Response.UserUpdateResponse
import com.example.app_sudamericana.API.Service.ReservationService
import com.example.app_sudamericana.R
import com.example.app_sudamericana.databinding.FragmentReservaBinding
import com.example.app_sudamericana.enviroments.Credentials
import com.example.app_sudamericana.enviroments.Credentials.USER_ID
import com.example.app_sudamericana.utils.DateCreatorFray
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ReservaFragment : Fragment() {
    val disposables: CompositeDisposable = CompositeDisposable()
    var reservation: ReservationService = ReservationService()
    var dateCreator: DateCreatorFray = DateCreatorFray()
    private lateinit var spInstance: SharedPreferences;
    private var _binding: FragmentReservaBinding? = null
    private val binding get() = _binding!!
    private lateinit var datePicker: MaterialTimePicker
    var dateCurrentPicker: String = "";
    var timeCurrentPicker: String = "";



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        _binding = FragmentReservaBinding.inflate(inflater, container, false)

        this.spInstance = requireActivity().getSharedPreferences(
            Credentials.NAME_PREFERENCES,
            Context.MODE_PRIVATE
        );

        binding.BtnSolicitar.setOnClickListener({ reservation() })
        this.cargarDataSelect()
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
                        val dateSplit = date.toString().split("-");
                        val ano = dateSplit[2];
                        val month = dateSplit[1];
                        val day = dateSplit[0];
                        dateCurrentPicker = "${ano}-${month}-${day}";

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
    fun timePickerCallback() {
        datePicker?.let { mDatePicker ->
            mDatePicker.addOnPositiveButtonClickListener {
                Log.wtf("timePicker1", mDatePicker.hour.toString())
                var format = if (mDatePicker.hour >= 13) {
                    "PM"
                } else {
                    "AM"
                }
                binding.TxtHora.text = "${mDatePicker.hour} : ${mDatePicker.minute} $format"


                timeCurrentPicker =
                    "${mDatePicker.hour}:${mDatePicker.minute}:00"
            }
            mDatePicker.addOnNegativeButtonClickListener {
                Log.wtf("timePicker2", mDatePicker.hour.toString())

            }
            mDatePicker.addOnCancelListener {
                //    Log.wtf("timePicker3",mDatePicker.hour.toString())
            }
            mDatePicker.addOnDismissListener {
                //    Log.wtf("timePicker4",mDatePicker.hour.toString())
            }
        }

    }


    private fun reservation() {
        val token = this.spInstance.getString(Credentials.TOKEN_JWT, "");
        val userID = this.spInstance.getString(Credentials.USER_ID, 1.toString());
        Toast.makeText(
            context,
            timeCurrentPicker,
            Toast.LENGTH_LONG
        ).show()
        if (token != null && userID != null) {
            reservation.seveReservation(
                token, RegisterReservation(
                    binding.TxtDescripcion.getText().toString(),
                    userID.toString().toInt(),
                    1,
                    1, "${dateCurrentPicker}T${timeCurrentPicker}"
                )
            )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<RegisterReservationResponse> {

                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onNext(t: RegisterReservationResponse) {
                        Toast.makeText(
                            context,
                            "Registrado Correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(
                            context,
                            e.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onComplete() {
                        disposables.clear()
                    }


                })
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun cargarDataSelect(){
        //1
        //Recuperamos los elementos del string array
        val countries = resources.getStringArray(R.array.country_location)
        //1
        //Creación del adapter
        val adapter = context?.let {
            ArrayAdapter(
                it, // Contexto
                R.layout.list_item, //Layout del diseño
                countries //Array
            )
        }
        //1
        //Agregamos el adapter al autoCompleteTextView
        with(binding.autoCompleteTextView) {
            setAdapter(adapter)
        }
    }



}