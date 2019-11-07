package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movierate")
data class MovieRate(
    val title: String,
    val rate: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}