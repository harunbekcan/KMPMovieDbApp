package com.harunbekcan.kmpmoviedbapp.android.home
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harunbekcan.kmpmoviedbapp.domain.model.Movie
import com.harunbekcan.kmpmoviedbapp.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
) : ViewModel() {

    private var currentPage = 1

    var uiState by mutableStateOf(HomeScreenState())
        private set

    init {
        loadMovies()
    }

    fun loadMovies(forceReload: Boolean = false) {
        if (uiState.loading) return

        if (forceReload) currentPage = 1

        updateState(loading = true, refreshing = currentPage == 1)

        viewModelScope.launch {
            try {
                val resultMovies = getMoviesUseCase(page = currentPage)

                val updatedMovies = if (currentPage == 1) resultMovies else uiState.movies + resultMovies

                currentPage++

                updateState(
                    loading = false,
                    refreshing = false,
                    loadFinished = resultMovies.isEmpty(),
                    movies = updatedMovies
                )

            } catch (error: Throwable) {
                updateState(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "Could not load movies: ${error.localizedMessage}"
                )
            }
        }
    }

    private fun updateState(
        loading: Boolean? = null,
        refreshing: Boolean? = null,
        loadFinished: Boolean? = null,
        movies: List<Movie>? = null,
        errorMessage: String? = null,
    ) {
        uiState = uiState.copy(
            loading = loading ?: uiState.loading,
            refreshing = refreshing ?: uiState.refreshing,
            loadFinished = loadFinished ?: uiState.loadFinished,
            movies = movies ?: uiState.movies,
            errorMessage = errorMessage ?: uiState.errorMessage
        )
    }
}

data class HomeScreenState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var movies: List<Movie> = listOf(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false
)
