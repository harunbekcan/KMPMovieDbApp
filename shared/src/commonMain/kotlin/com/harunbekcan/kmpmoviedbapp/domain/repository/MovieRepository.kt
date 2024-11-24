package com.harunbekcan.kmpmoviedbapp.domain.repository

import com.harunbekcan.kmpmoviedbapp.domain.model.Movie

internal interface MovieRepository {
    suspend fun getMovies(page: Int): List<Movie>
    suspend fun getMovie(movieId: Int): Movie
}