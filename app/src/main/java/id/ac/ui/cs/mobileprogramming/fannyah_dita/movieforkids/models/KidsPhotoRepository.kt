package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class KidsPhotoRepository(application: Application) {
    private var kidsPhotoDao: KidsPhotoDao
    private var allPhotos: LiveData<List<KidsPhoto>>
    private lateinit var photo: LiveData<KidsPhoto>

    init {
        val database: KidsPhotoDatabase = KidsPhotoDatabase.getInstance(
            application.applicationContext
        )!!
        kidsPhotoDao = database.kidsPhotoDao()
        allPhotos = kidsPhotoDao.getAllPhotos()
    }


    fun insert(kidsPhoto: KidsPhoto) {
        InsertPhotoAsyncTask(kidsPhotoDao).execute(kidsPhoto)
    }

    fun detailPhoto(photoId: Int): LiveData<KidsPhoto> {
        photo = kidsPhotoDao.detailPhoto(photoId)
        return photo
    }

    fun getAllPhotos(): LiveData<List<KidsPhoto>> {
        return allPhotos
    }

    private class InsertPhotoAsyncTask(val kidsPhotoDao: KidsPhotoDao) :
        AsyncTask<KidsPhoto, Unit, Unit>() {

        override fun doInBackground(vararg p0: KidsPhoto?) {
            kidsPhotoDao.insert(p0[0]!!)
        }
    }
}