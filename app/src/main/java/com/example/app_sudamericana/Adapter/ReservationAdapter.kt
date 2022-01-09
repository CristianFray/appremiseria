package com.example.app_sudamericana.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_sudamericana.API.Domain.Response.ReservationResponse
import com.example.app_sudamericana.API.Domain.Response.ReservationResponseItem
import com.example.app_sudamericana.R
import com.google.android.material.textview.MaterialTextView

class ReservationsAdapter(var context: Context, val reservations: ArrayList<ReservationResponseItem>): RecyclerView.Adapter<ReservationsAdapter.ReservationHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationHolder {
        return ReservationHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_my_post, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    override fun onBindViewHolder(holder: ReservationHolder, position: Int) {
        holder.bind(reservations[position], context)
    }

    class ReservationHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleReservation: TextView = itemView.findViewById(R.id.textViewTitleMyPost)


        fun bind(data: ReservationResponseItem, context: Context) {
            titleReservation.text = data.idReservation.toString()
        }
    }
}





















