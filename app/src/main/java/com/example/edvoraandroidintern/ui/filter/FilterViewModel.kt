package com.example.edvoraandroidintern.ui.filter

import androidx.lifecycle.ViewModel
import com.example.edvoraandroidintern.data.local.PreferencesRepository
import com.example.edvoraandroidintern.data.network.RidesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val ridesRepository: RidesRepository,
    private val PreferencesRepository: PreferencesRepository,
) : ViewModel() {

    val states = ridesRepository.stateNames
    val cities = ridesRepository.cityNames

    val currentState = PreferencesRepository.stateFlow
    val currentCity = PreferencesRepository.cityFlow

    fun updateState(state: String) {
        PreferencesRepository.updateState(state)
        updateRides()
    }

    fun updateCity(city: String) {
        PreferencesRepository.updateCity(city)
        updateRides()
    }

    private fun updateRides() {
        ridesRepository.filterRides(currentState.value, currentCity.value)
    }

}