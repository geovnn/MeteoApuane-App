package com.geovnn.meteoapuane.domain.use_cases

import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.domain.models.HomePage
import com.geovnn.meteoapuane.domain.repository.MeteoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHomePage @Inject constructor(
    private val repository: MeteoRepository
) {
    operator fun invoke(): Flow<Resource<HomePage>> {
        return repository.getHomePage()
    }
}