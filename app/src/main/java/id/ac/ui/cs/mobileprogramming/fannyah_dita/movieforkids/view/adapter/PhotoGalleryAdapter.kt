package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.KidsPhoto
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment.PhotoGalleryFragmentDirections
import kotlinx.android.synthetic.main.item_photos_list.view.*

class PhotoGalleryAdapter : RecyclerView.Adapter<PhotoGalleryAdapter.ViewHolder>() {

    private var photos: List<KidsPhoto> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_photos_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.image_photo_list.setImageURI(Uri.parse(photos[position].imageUri))
        holder.view.title_photo_list.text = photos[position].movieTitle
        holder.view.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(PhotoGalleryFragmentDirections.actionToDetailPhoto().setId(position))
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    fun setPhotos(photos: List<KidsPhoto>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}