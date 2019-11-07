package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class MovieRateRepository(application: Application) {
    private var movieRateDao: MovieRateDao
    private var allRates: LiveData<List<MovieRate>>

    init {
        val database: MovieRateDatabase = MovieRateDatabase.getInstance(
            application.applicationContext
        )!!
        movieRateDao = database.movieRateDao()
        allRates = movieRateDao.getAllRates()
    }

    fun getAllRates(): LiveData<List<MovieRate>> {
        return allRates
    }

    fun insert(movieRate: MovieRate) {
        InsertRatingAsyncTask(movieRateDao).execute(movieRate)
    }

    private class InsertRatingAsyncTask(val movieRateDao: MovieRateDao) : AsyncTask<MovieRate, Unit, Unit>() {

        override fun doInBackground(vararg p0: MovieRate?) {
            movieRateDao.insert(p0[0]!!)
        }
    }
}