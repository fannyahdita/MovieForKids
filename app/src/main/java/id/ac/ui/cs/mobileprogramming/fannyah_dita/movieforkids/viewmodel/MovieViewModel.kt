package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.Movie
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.MovieRepository

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = MovieRepository(application)
    private var allMovies: LiveData<List<Movie>> = repository.getAllMovies()
    private lateinit var detailMovie: LiveData<Movie>

    fun insert(movie: Movie) {
        repository.insert(movie)
    }

    fun deleteMovie(movieId: Int) {
        repository.deleteMovie(movieId)
    }

    fun deleteAllMovies() {
        repository.deleteAllMovies()
    }

    fun detailMovie(movieId: Int): LiveData<Movie> {
        detailMovie = repository.detailMovie(movieId)
        return detailMovie
    }

    fun getAllMovies(): LiveData<List<Movie>> {
        return allMovies
    }
}