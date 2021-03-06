package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment.gallerytabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.MovieRate
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter.MovieRateAdapter
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.MovieRateViewModel
import kotlinx.android.synthetic.main.fragment_recyclerview_rating.*

class MovieRateFragment : Fragment() {

    private lateinit var movieRateViewModel: MovieRateViewModel
    private var movieRateAdapter = MovieRateAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recyclerview_rating, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieRateViewModel = ViewModelProviders.of(this).get(MovieRateViewModel::class.java)

        observeViewModel()

    }


    private fun observeViewModel() {
        movieRateViewModel.getAllRates().observe(this,
            Observer<List<MovieRate>> { list ->
                movieRateAdapter.setRating(list)
                setLayoutInFragment(list.size)
            }
        )
    }

    private fun setLayoutInFragment(length: Int) {

        if (length > 0) {
            recyclerview_rating.visibility = View.VISIBLE
            empty_photo_container_rating.visibility = View.GONE

            recyclerview_rating.layoutManager = LinearLayoutManager(context)
            recyclerview_rating.adapter = movieRateAdapter
        } else {
            recyclerview_rating.visibility = View.GONE
            empty_photo_container_rating.visibility = View.VISIBLE
        }
    }
}