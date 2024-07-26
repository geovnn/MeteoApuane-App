package com.geovnn.meteoapuane.presentation.nowcasting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geovnn.meteoapuane.domain.models.NowcastingPage
import com.geovnn.meteoapuane.domain.models.WebcamPage
import com.geovnn.meteoapuane.domain.use_cases.GetNowcastingPage
import com.geovnn.meteoapuane.domain.use_cases.GetWebcamPage
import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.presentation.webcam.WebcamState
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
class NowcastingViewModel @Inject constructor(
    private val getNowcastingPage: GetNowcastingPage
) : ViewModel() {

    private val _state = MutableStateFlow(NowcastingState())
    val state: StateFlow<NowcastingState> = _state.asStateFlow()

    private var updateJob: Job? = null

    init {
//        updateData()
    }

    fun updateData() {
        updateJob = viewModelScope.launch(Dispatchers.IO) {
            getNowcastingPage().onEach {result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            nowcastingPage = result.data ?: NowcastingPage(),
                        )
                    }
                    is Resource.Error -> {
                        Log.d("Debug",result.message?: "e' null")
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Errore caricamento",
                            nowcastingPage = NowcastingPage(),
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