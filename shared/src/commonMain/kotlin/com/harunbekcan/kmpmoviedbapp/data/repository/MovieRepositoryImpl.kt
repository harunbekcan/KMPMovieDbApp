package com.harunbekcan.kmpmoviedbapp.data.repository

import com.harunbekcan.kmpmoviedbapp.data.mapper.toMovie
import com.harunbekcan.kmpmoviedbapp.data.remote.RemoteDataSource
import com.harunbekcan.kmpmoviedbapp.domain.model.Movie
import com.harunbekcan.kmpmoviedbapp.domain.repository.MovieRepository

internal class MovieRepositoryImpl(private val remoteDateSource: RemoteDataSource) :
    MovieRepository {
    override suspend fun getMovies(page: Int): List<Movie> {
        return remoteDateSource.getMovies(page = page).results.map {
            it.toMovie()
        }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return remoteDateSource.getMovie(movieId = movieId).toMovie()
    }
}