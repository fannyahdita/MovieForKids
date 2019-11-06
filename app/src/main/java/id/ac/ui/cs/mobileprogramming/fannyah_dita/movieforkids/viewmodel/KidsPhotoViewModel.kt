package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.KidsPhoto
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.KidsPhotoRepository

class KidsPhotoViewModel(application: Application) : AndroidViewModel(application) {
    private var repository = KidsPhotoRepository(application)
    private var allPhotos: LiveData<List<KidsPhoto>> = repository.getAllPhotos()
    private lateinit var photo: LiveData<KidsPhoto>

    fun insert(kidsPhoto: KidsPhoto) {
        repository.insert(kidsPhoto)
    }

    fun getPhoto(photoId: Int): LiveData<KidsPhoto> {
        photo = repository.getPhoto(photoId)
        return photo
    }

    fun getAllPhotos(): LiveData<List<KidsPhoto>> {
        return allPhotos
    }

}