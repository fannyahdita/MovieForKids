package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    var posterRes: Int,
    var title: String,
    var year: String,
    var director: String,
    var synopsis: String,
    var trailers: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}