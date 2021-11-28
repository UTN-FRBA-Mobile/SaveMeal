package com.example.savemeal.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savemeal.MealDI
import com.example.savemeal.ReservationDI
import com.example.savemeal.reservations.ReservationOption

class ReservationListViewModel : ViewModel() {

    private val reservationRepository: ReservationRepository = ReservationDI.provideReservationRepository()

    suspend fun getReservations(): LiveData<List<ReservationOption>> {
        val mutableLiveData = MutableLiveData<List<ReservationOption>>()
        mutableLiveData.value = reservationRepository.getReservations()
        return mutableLiveData
    }

}

