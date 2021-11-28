package com.example.savemeal.domain.reservation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.savemeal.ReservationDI

class ReservationListViewModel : ViewModel() {

    private val reservationRepository: ReservationRepository = ReservationDI.provideReservationRepository()

    suspend fun getUserReservations(): LiveData<List<ReservationOption>> {
        val mutableLiveData = MutableLiveData<List<ReservationOption>>()
        mutableLiveData.value = reservationRepository.getUserReservations()
        return mutableLiveData
    }

    suspend fun getBusinessReservations(): LiveData<List<ReservationOption>> {
        val mutableLiveData = MutableLiveData<List<ReservationOption>>()
        mutableLiveData.value = reservationRepository.getBusinessReservations()
        return mutableLiveData
    }

}

