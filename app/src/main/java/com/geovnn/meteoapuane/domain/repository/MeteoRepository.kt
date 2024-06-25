package com.geovnn.meteoapuane.domain.repository

import com.geovnn.meteoapuane.domain.utils.Resource
import com.geovnn.meteoapuane.domain.models.ConfiniPage
import com.geovnn.meteoapuane.domain.models.HomePage
import com.geovnn.meteoapuane.domain.models.IncendiPage
import com.geovnn.meteoapuane.domain.models.MontagnaPage
import com.geovnn.meteoapuane.domain.models.ProvinciaPage
import com.geovnn.meteoapuane.domain.models.ViabilitaPage
import com.geovnn.meteoapuane.domain.models.WebcamPage
import kotlinx.coroutines.flow.Flow


interface MeteoRepository {

    fun getHomePage(): Flow<Resource<HomePage>>

    fun getProvinciaPage(): Flow<Resource<ProvinciaPage>>

    fun getConfiniPage(): Flow<Resource<ConfiniPage>>

    fun getMontagnaPage(): Flow<Resource<MontagnaPage>>

    fun getViabilitaPage(): Flow<Resource<ViabilitaPage>>

    fun getIncendiPage(): Flow<Resource<IncendiPage>>

    fun getWebcamPage(): Flow<Resource<WebcamPage>>

}