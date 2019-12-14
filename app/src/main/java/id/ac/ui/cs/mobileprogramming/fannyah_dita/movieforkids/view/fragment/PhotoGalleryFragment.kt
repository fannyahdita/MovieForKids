package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_photos_list.*

class PhotoGalleryFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoGalleryFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewpager_gallery.adapter = ViewPagerAdapter(childFragmentManager, context)
        tabs.setupWithViewPager(viewpager_gallery)

        button_add_photo_list.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(PhotoGalleryFragmentDirections.actionToAddPhoto())
        }

        button_add_rate_list.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(PhotoGalleryFragmentDirections.actionToAddRating())
        }

        button_add_reminder.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(PhotoGalleryFragmentDirections.actionToAddReminder())
        }
    }

}