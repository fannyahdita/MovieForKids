package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment.gallerytabs.MovieRateFragment
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment.gallerytabs.PhotoFragment

class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
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
            0 -> "Photos"
            else -> "Rating"
        }
    }
}