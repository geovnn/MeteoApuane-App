package com.geovnn.meteoapuane.presentation.confini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.domain.models.ConfiniPage
import com.geovnn.meteoapuane.domain.use_cases.GetConfiniPage
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
class ConfiniViewModel @Inject constructor(
    private val getConfiniPage: GetConfiniPage
) : ViewModel() {

    private val _state = MutableStateFlow(ConfiniUiState())
    val state: StateFlow<ConfiniUiState> = _state.asStateFlow()

    private var updateJob: Job? = null

    fun updateData() {
        updateJob = viewModelScope.launch(Dispatchers.IO) {
            getConfiniPage().onEach {result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "",
                            confiniPage = result.data ?: ConfiniPage(),
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Errore caricamento",
                            confiniPage = result.data ?: ConfiniPage(),
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