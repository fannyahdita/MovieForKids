package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment.gallerytabs

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.KidsPhoto
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter.PhotoGalleryAdapter
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.KidsPhotoViewModel
import kotlinx.android.synthetic.main.fragment_recyclerview_gallery.*
import kotlin.math.roundToInt

class PhotoFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoFragment()
    }

    private lateinit var kidsPhotoViewModel: KidsPhotoViewModel
    private var photoGalleryAdapter = PhotoGalleryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recyclerview_gallery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        kidsPhotoViewModel = ViewModelProviders.of(this).get(KidsPhotoViewModel::class.java)

        observeViewModel()

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
            recyclerview.visibility = View.VISIBLE
            empty_photo_container.visibility = View.GONE

            recyclerview.layoutManager =
                GridLayoutManager(activity, columns) as RecyclerView.LayoutManager?
            recyclerview.adapter = photoGalleryAdapter
        } else {
            recyclerview.visibility = View.GONE
            empty_photo_container.visibility = View.VISIBLE
        }
    }
}