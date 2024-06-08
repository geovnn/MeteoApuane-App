package com.geovnn.meteoapuane.data.repository

import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.data.remote.MeteoapuaneApi
import com.geovnn.meteoapuane.domain.models.ConfiniPage
import com.geovnn.meteoapuane.domain.models.HomePage
import com.geovnn.meteoapuane.domain.models.MontagnaPage
import com.geovnn.meteoapuane.domain.models.ProvinciaPage
import com.geovnn.meteoapuane.domain.models.ViabilitaPage
import com.geovnn.meteoapuane.domain.repository.MeteoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MeteoRepositoryImplementation @Inject constructor(
    private val api: MeteoapuaneApi
): MeteoRepository {

    override fun getHomePage(): Flow<Resource<HomePage>> = flow {
        emit(Resource.Loading())
        try {
            val homeData = api.getHomeData()
            emit(Resource.Success(data = homeData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getProvinciaPage(): Flow<Resource<ProvinciaPage>> = flow {
        emit(Resource.Loading())
        try {
            val provinciaData = api.getProvinciaData()
            emit(Resource.Success(data = provinciaData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getConfiniPage(): Flow<Resource<ConfiniPage>> = flow {
        emit(Resource.Loading())
        try {
            val confiniData = api.getConfiniData()
            emit(Resource.Success(data = confiniData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getMontagnaPage(): Flow<Resource<MontagnaPage>> = flow {
        emit(Resource.Loading())
        try {
            val montagnaData = api.getMontagnaData()
            emit(Resource.Success(data = montagnaData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }

    override fun getViabilitaPage(): Flow<Resource<ViabilitaPage>> = flow {
        emit(Resource.Loading())
        try {
            val viabilitaData = api.getViabilitaData()
            emit(Resource.Success(data = viabilitaData))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}