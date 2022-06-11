package com.example.edvoraandroidintern.ui.upcoming
import androidx.lifecycle.ViewModel
import com.example.edvoraandroidintern.data.network.RidesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val ridesRepository: RidesRepository,
) : ViewModel() {
    val upcomingRides = ridesRepository.upcomingRides
    val stationCode = ridesRepository.stationCode
}