package com.geovnn.meteoapuane.domain.use_cases

import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.domain.models.MontagnaPage
import com.geovnn.meteoapuane.domain.repository.MeteoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMontagnaPage @Inject constructor(
    private val repository: MeteoRepository
) {
    operator fun invoke(): Flow<Resource<MontagnaPage>> {
        return repository.getMontagnaPage()
    }
}