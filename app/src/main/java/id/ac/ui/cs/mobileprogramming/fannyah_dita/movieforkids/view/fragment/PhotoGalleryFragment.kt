package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.KidsPhoto
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter.PhotoGalleryAdapter
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.KidsPhotoViewModel
import kotlinx.android.synthetic.main.fragment_photos_list.*
import kotlin.math.roundToInt

class PhotoGalleryFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }

    private lateinit var kidsPhotoViewModel: KidsPhotoViewModel
    private var photoGalleryAdapter = PhotoGalleryAdapter()
//    private var length = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        kidsPhotoViewModel = ViewModelProviders.of(this).get(KidsPhotoViewModel::class.java)

        observeViewModel()

        button_add_photo_list.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(PhotoGalleryFragmentDirections.actionToAddPhoto())
        }
    }

    private fun observeViewModel() {
        kidsPhotoViewModel.getAllPhotos().observe(this,
            Observer<List<KidsPhoto>> { list ->
                photoGalleryAdapter.setPhotos(list)
                setLayoutInFragment(list.size)
            }
        )
    }

    private fun setLayoutInFragment(length: Int) {
        val display = activity?.windowManager?.defaultDisplay
        val outMetrics = DisplayMetrics()
        display?.getMetrics(outMetrics)

        val density = resources.displayMetrics.density
        val dpWidth = outMetrics.widthPixels / density
        val columns = (dpWidth / 150).roundToInt()

        if (length > 0) {
            recyclerview_photos.visibility = View.VISIBLE
            empty_photo_container.visibility = View.GONE

            recyclerview_photos.layoutManager =
                GridLayoutManager(activity, columns) as RecyclerView.LayoutManager?
            recyclerview_photos.adapter = photoGalleryAdapter
        } else {
            recyclerview_photos.visibility = View.GONE
            empty_photo_container.visibility = View.VISIBLE
        }
    }
}