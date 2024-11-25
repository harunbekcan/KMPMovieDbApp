package com.harunbekcan.kmpmoviedbapp.android.di

import com.harunbekcan.kmpmoviedbapp.android.detail.DetailViewModel
import com.harunbekcan.kmpmoviedbapp.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { params -> DetailViewModel(getMovieUseCase = get(), movieId = params.get()) }
}