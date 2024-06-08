package com.geovnn.meteoapuane.domain.use_cases

import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.domain.models.ProvinciaPage
import com.geovnn.meteoapuane.domain.repository.MeteoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProvinciaPage @Inject constructor(
    private val repository: MeteoRepository
) {
    operator fun invoke(): Flow<Resource<ProvinciaPage>> {
        return repository.getProvinciaPage()
    }
}