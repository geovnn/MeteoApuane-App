package com.geovnn.meteoapuane.presentation.incendi

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geovnn.meteoapuane.domain.models.HomePage
import com.geovnn.meteoapuane.domain.models.IncendiPage
import com.geovnn.meteoapuane.domain.use_cases.GetHomePage
import com.geovnn.meteoapuane.domain.use_cases.GetIncendiPage
import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.presentation.home.HomeState
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
class IncendiViewModel @Inject constructor(
    private val getIncendiPage: GetIncendiPage
) : ViewModel() {

    private val _state = MutableStateFlow(IncendiState())
    val state: StateFlow<IncendiState> = _state.asStateFlow()

    private var updateJob: Job? = null

    init {
        updateData()
    }

    fun updateData() {
        updateJob = viewModelScope.launch(Dispatchers.IO) {
            getIncendiPage().onEach {result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            incendiPage = result.data ?: IncendiPage(),
                        )
                    }
                    is Resource.Error -> {
                        Log.d("Debug",result.message?: "e' null")
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Errore caricamento",
                            incendiPage = IncendiPage(),
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