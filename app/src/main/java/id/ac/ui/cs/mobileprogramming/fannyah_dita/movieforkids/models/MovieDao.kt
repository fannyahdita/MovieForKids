package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert
    fun insert (movie: Movie)

    @Query("DELETE FROM movie WHERE id=:movieId")
    fun deleteMovie(movieId: Int)

    @Query("DELETE FROM movie")
    fun deleteAllMovies()

    @Query("SELECT * FROM movie WHERE id=:movieId")
    fun detailMovie(movieId: Int) : LiveData<Movie>

    @Query("SELECT * FROM movie" )
    fun getAllMovies(): LiveData<List<Movie>>
}