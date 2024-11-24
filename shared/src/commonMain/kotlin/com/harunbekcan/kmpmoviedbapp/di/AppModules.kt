package com.harunbekcan.kmpmoviedbapp.di

import com.harunbekcan.kmpmoviedbapp.data.remote.MovieService
import com.harunbekcan.kmpmoviedbapp.data.remote.RemoteDataSource
import com.harunbekcan.kmpmoviedbapp.data.repository.MovieRepositoryImpl
import com.harunbekcan.kmpmoviedbapp.domain.repository.MovieRepository
import com.harunbekcan.kmpmoviedbapp.domain.usecase.GetMovieUseCase
import com.harunbekcan.kmpmoviedbapp.domain.usecase.GetMoviesUseCase
import com.harunbekcan.kmpmoviedbapp.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { MovieService() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    factory { GetMoviesUseCase() }
    factory { GetMovieUseCase() }
}

private val appModules = listOf(domainModule, dataModule, utilityModule)

fun getAppModules() = appModules