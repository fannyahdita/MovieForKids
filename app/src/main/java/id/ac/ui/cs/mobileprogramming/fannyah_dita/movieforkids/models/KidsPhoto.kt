package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class KidsPhoto(var imageUri: String?) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}