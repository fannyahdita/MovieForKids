package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieRateDao {
    @Insert
    fun insert(movieRate: MovieRate)

    @Query("SELECT * FROM movierate")
    fun getAllRates(): LiveData<List<MovieRate>>
}
