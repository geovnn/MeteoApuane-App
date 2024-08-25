package com.geovnn.meteoapuane.domain.use_cases

import com.geovnn.meteoapuane.domain.models.NowcastingPage
import com.geovnn.meteoapuane.domain.repository.MeteoRepository
import com.geovnn.meteoapuane.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowcastingPage @Inject constructor(
    private val repository: MeteoRepository
) {
    operator fun invoke(): Flow<Resource<NowcastingPage>> {
        return repository.getNowcastingPage()
    }
}