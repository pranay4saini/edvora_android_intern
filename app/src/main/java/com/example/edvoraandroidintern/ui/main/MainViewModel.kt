package com.example.edvoraandroidintern.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edvoraandroidintern.data.Status
import com.example.edvoraandroidintern.data.local.PreferencesRepository
import com.example.edvoraandroidintern.data.model.User
import com.example.edvoraandroidintern.data.network.RidesRepository
import com.example.edvoraandroidintern.data.network.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val ridesRepository: RidesRepository,
    private val PreferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    val tapsCount = ridesRepository.tapsCount

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val userResponse = userRepository.getUserData()
            if (userResponse.status == Status.SUCCESS)
                _user.value = userResponse.data
        }
    }

    fun updateRides(stationCode: Int) {
        val stateFilter = PreferencesRepository.stateFlow.value
        val cityFilter = PreferencesRepository.cityFlow.value
        viewModelScope.launch(Dispatchers.IO) {
            ridesRepository.updateRides(stationCode, stateFilter, cityFilter)
        }
    }

}