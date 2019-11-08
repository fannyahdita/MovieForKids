package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment.gallerytabs.MovieRateFragment
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment.gallerytabs.PhotoFragment

class ViewPagerAdapter(fm: FragmentManager, nContext: Context?) : FragmentPagerAdapter(fm) {

    val context = nContext

    private val pages = listOf(
        PhotoFragment(),
        MovieRateFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context?.getString(R.string.photos_tabs)
            else -> context?.getString(R.string.rating_tabs)
        }
    }
}