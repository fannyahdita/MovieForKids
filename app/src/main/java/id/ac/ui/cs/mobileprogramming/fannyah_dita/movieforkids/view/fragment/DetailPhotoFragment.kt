package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.KidsPhotoViewModel
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import kotlinx.android.synthetic.main.fragment_photo_detail.*

class DetailPhotoFragment : Fragment() {
    companion object {
        fun newInstance() = DetailPhotoFragment()
    }

    private lateinit var viewModel: KidsPhotoViewModel
    private var photoId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            photoId = DetailPhotoFragmentArgs.fromBundle(it).id
        }

        viewModel = ViewModelProviders.of(this).get(KidsPhotoViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {

        viewModel.detailPhoto(photoId + 1).observe(this, Observer { photo ->
            photo?.let {
                title_photo_detail.text = photo.movieTitle.capitalize()
                kidsphoto_detail.setImageURI(Uri.parse(photo.imageUri))
                desc_photo_detail.text = Html.fromHtml(getString(R.string.desc_photo_detail, photo.description))
            }
        })
    }
}