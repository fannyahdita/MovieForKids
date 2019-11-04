package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class MovieRepository(application: Application) {
    private var movieDao: MovieDao
    private var allMovies: LiveData<List<Movie>>
    private lateinit var detailMovie: LiveData<Movie>

    init {
        val database: MovieDatabase = MovieDatabase.getInstance(
            application.applicationContext
        )!!
        movieDao = database.movieDao()
        allMovies = movieDao.getAllMovies()
    }

    fun insert(movie: Movie) {
        val insertMovieAsyncTask = InsertMovieAsyncTask(movieDao).execute(movie)
    }

    fun deleteMovie(movieId: Int) {
        val deleteMovieAsyncTask = DeleteMovieAsyncTask(movieDao).execute(movieId)
    }

    fun deleteAllMovies() {
        val deleteAllMoviesAsyncTask = DeleteAllMoviesAsyncTask(movieDao).execute()
    }

    fun detailMovie(movieId: Int): LiveData<Movie> {
        detailMovie = movieDao.detailMovie(movieId)
        return detailMovie
    }

    fun getAllMovies(): LiveData<List<Movie>> {
        return allMovies
    }

    private class InsertMovieAsyncTask(val movieDao: MovieDao) : AsyncTask<Movie, Unit, Unit>() {

        override fun doInBackground(vararg p0: Movie?) {
            movieDao.insert(p0[0]!!)
        }
    }


    private class DeleteMovieAsyncTask(val movieDao: MovieDao) : AsyncTask<Int, Unit, Unit>() {

        override fun doInBackground(vararg p0: Int?) {
            movieDao.deleteMovie(p0[0]!!)
        }
    }

    private class DeleteAllMoviesAsyncTask(val movieDao: MovieDao) : AsyncTask<Int, Unit, Unit>() {

        override fun doInBackground(vararg p0: Int?) {
            movieDao.deleteAllMovies()
        }
    }


}