package com.geovnn.meteoapuane.presentation.webcam

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geovnn.meteoapuane.domain.models.IncendiPage
import com.geovnn.meteoapuane.domain.models.WebcamPage
import com.geovnn.meteoapuane.domain.use_cases.GetIncendiPage
import com.geovnn.meteoapuane.domain.use_cases.GetWebcamPage
import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.presentation.incendi.IncendiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebcamViewModel @Inject constructor(
    private val getWebcamPage: GetWebcamPage
) : ViewModel() {

    private val _state = MutableStateFlow(WebcamState())
    val state: StateFlow<WebcamState> = _state.asStateFlow()

    private var updateJob: Job? = null

    init {
//        updateData()
    }

    fun updateData() {
        updateJob = viewModelScope.launch(Dispatchers.IO) {
            getWebcamPage().onEach {result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            webcamPage = result.data ?: WebcamPage(),
                        )
                    }
                    is Resource.Error -> {
                        Log.d("Debug",result.message?: "e' null")
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Errore caricamento",
                            webcamPage = WebcamPage(),
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}