package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.Movie
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.MovieRate
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.MovieRateRepository
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.MovieRepository

class MovieRateViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = MovieRateRepository(application)
    private var allRates: LiveData<List<MovieRate>> = repository.getAllRates()

    fun insert(movieRate: MovieRate) {
        repository.insert(movieRate)
    }

    fun getAllRates(): LiveData<List<MovieRate>> {
        return allRates
    }
}