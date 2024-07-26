package com.geovnn.meteoapuane.di

import com.geovnn.meteoapuane.data.remote.MeteoapuaneScrape
import com.geovnn.meteoapuane.data.repository.MeteoRepositoryImplementation
import com.geovnn.meteoapuane.domain.repository.MeteoRepository
import com.geovnn.meteoapuane.domain.use_cases.GetConfiniPage
import com.geovnn.meteoapuane.domain.use_cases.GetHomePage
import com.geovnn.meteoapuane.domain.use_cases.GetIncendiPage
import com.geovnn.meteoapuane.domain.use_cases.GetMontagnaPage
import com.geovnn.meteoapuane.domain.use_cases.GetNowcastingPage
import com.geovnn.meteoapuane.domain.use_cases.GetProvinciaPage
import com.geovnn.meteoapuane.domain.use_cases.GetViabilitaPage
import com.geovnn.meteoapuane.domain.use_cases.GetWebcamPage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMeteoapuaneApi(): MeteoapuaneScrape {
        return MeteoapuaneScrape()
    }

    @Provides
    @Singleton
    fun provideMeteoRepository(api: MeteoapuaneScrape): MeteoRepository {
        return MeteoRepositoryImplementation(api)
    }

    @Provides
    @Singleton
    fun provideGetHomePageUseCase(repository: MeteoRepository): GetHomePage {
        return GetHomePage(repository)
    }

    @Provides
    @Singleton
    fun provideGetConfiniPageUseCase(repository: MeteoRepository): GetConfiniPage {
        return GetConfiniPage(repository)
    }

    @Provides
    @Singleton
    fun provideGetMontagnaPageUseCase(repository: MeteoRepository): GetMontagnaPage {
        return GetMontagnaPage(repository)
    }

    @Provides
    @Singleton
    fun provideGetProvinciaPageUseCase(repository: MeteoRepository): GetProvinciaPage {
        return GetProvinciaPage(repository)
    }

    @Provides
    @Singleton
    fun provideGetViabilitaPageUseCase(repository: MeteoRepository): GetViabilitaPage {
        return GetViabilitaPage(repository)
    }

    @Provides
    @Singleton
    fun provideGetIncendiPageUseCase(repository: MeteoRepository): GetIncendiPage {
        return GetIncendiPage(repository)
    }

    @Provides
    @Singleton
    fun provideGetWebcamPageUseCase(repository: MeteoRepository): GetWebcamPage {
        return GetWebcamPage(repository)
    }

    @Provides
    @Singleton
    fun provideGetNowcastingPageUseCase(repository: MeteoRepository): GetNowcastingPage {
        return GetNowcastingPage(repository)
    }
}

