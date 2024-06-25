package com.geovnn.meteoapuane.presentation.webcam

import com.geovnn.meteoapuane.domain.models.IncendiPage
import com.geovnn.meteoapuane.domain.models.WebcamPage

data class WebcamState(
    val webcamPage: WebcamPage = WebcamPage(),
    val isLoading: Boolean = true,
    val error: String = "",
)