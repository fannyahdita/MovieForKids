package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KidsPhotoDao {
    @Insert
    fun insert(kidsPhoto: KidsPhoto)

    @Query("SELECT * FROM photo WHERE id=:photoId")
    fun detailPhoto(photoId: Int): LiveData<KidsPhoto>

    @Query("DELETE FROM photo")
    fun deleteAllPhotos()

    @Query("SELECT * FROM photo")
    fun getAllPhotos(): LiveData<List<KidsPhoto>>
}