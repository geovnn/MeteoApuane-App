package com.geovnn.meteoapuane.presentation.viabilita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.domain.models.ViabilitaPage
import com.geovnn.meteoapuane.domain.use_cases.GetViabilitaPage
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
class ViabilitaViewModel @Inject constructor(
    private val getViabilitaPage: GetViabilitaPage
) : ViewModel() {

    private val _state = MutableStateFlow(ViabilitaUiState())
    val state: StateFlow<ViabilitaUiState> = _state.asStateFlow()

    private var updateJob: Job? = null

    fun updateData() {
        updateJob = viewModelScope.launch(Dispatchers.IO) {
            println("QUA AVVIA DAL VIEWMODEL")
            getViabilitaPage().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            viabilitaPage = result.data ?: ViabilitaPage()
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Errore caricamento",
                            viabilitaPage = ViabilitaPage()
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}