package com.geovnn.meteoapuane.presentation.montagna

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.domain.models.MontagnaPage
import com.geovnn.meteoapuane.domain.use_cases.GetMontagnaPage
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
class MontagnaViewModel @Inject constructor(
    private val getMontagnaPage: GetMontagnaPage
) : ViewModel() {

    private val _state = MutableStateFlow(MontagnaUiState())
    val state: StateFlow<MontagnaUiState> = _state.asStateFlow()

    private var updateJob: Job? = null

    init {
        updateData()
    }

    fun updateData() {
        updateJob = viewModelScope.launch(Dispatchers.IO) {
            getMontagnaPage().onEach {result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            montagnaPage = result.data ?: MontagnaPage()

                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Errore caricamento",
                            montagnaPage = MontagnaPage(),
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