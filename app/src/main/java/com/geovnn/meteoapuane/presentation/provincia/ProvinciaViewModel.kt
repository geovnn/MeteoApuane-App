package com.geovnn.meteoapuane.presentation.provincia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.domain.models.ProvinciaPage
import com.geovnn.meteoapuane.domain.use_cases.GetProvinciaPage
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
class ProvinciaViewModel @Inject constructor(
    private val getProvinciaPage: GetProvinciaPage
) : ViewModel() {

    private val _state = MutableStateFlow(ProvinciaUiState())
    val state: StateFlow<ProvinciaUiState> = _state.asStateFlow()

    private var updateJob: Job? = null

    fun updateData() {
        updateJob = viewModelScope.launch(Dispatchers.IO) {
            getProvinciaPage().onEach {result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            provinciaPage = result.data ?: ProvinciaPage()
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Errore caricamento",
                            provinciaPage = ProvinciaPage()
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