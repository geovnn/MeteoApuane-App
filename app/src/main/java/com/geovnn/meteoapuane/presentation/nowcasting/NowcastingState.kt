package com.geovnn.meteoapuane.presentation.nowcasting

import android.graphics.Bitmap
import com.geovnn.meteoapuane.domain.models.NowcastingPage
import com.geovnn.meteoapuane.domain.models.WebcamPage

data class NowcastingState(
    val nowcastingPage: NowcastingPage = NowcastingPage(),
    val isLoading: Boolean = true,
    val error: String = "",
)
