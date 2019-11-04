package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.squareup.picasso.Picasso
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var viewModel: MovieViewModel
    private var trailerId = ""
    private var movieId = 0
    private var apiKey = "AIzaSyAOKpligJrMKe-mS769IX438NPs5tN6flM"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            movieId = MovieDetailFragmentArgs.fromBundle(it).id
        }

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        observeViewModel()

        Log.wtf("trailerid : ", "ini trailer $trailerId")

        val imageUrl = "http://img.youtube.com/vi/$trailerId/0.jpg"
        Log.wtf("url: ", imageUrl)
        Picasso.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(imageview_thumbnail)

        Log.wtf("trailerid : ", "ini trailer lagi $trailerId")
        imageview_thumbnail.setOnClickListener {
            Log.wtf("trailerid : ", "ini trailer lagi lagi $trailerId")
            val intent = YouTubeStandalonePlayer.createVideoIntent(activity, apiKey, trailerId)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {

        viewModel.detailMovie(movieId + 1).observe(this, Observer { movie ->
            movie?.let {
                movie_title.text = movie.title
                movie_poster.setImageResource(movie.posterRes)
                movie_year.text = Html.fromHtml(getString(R.string.movie_year, movie.year))
                movie_director.text =
                    Html.fromHtml(getString(R.string.movie_director, movie.director))
                movie_synopsis.text =
                    Html.fromHtml(getString(R.string.movie_synopsis, movie.synopsis))
                movie_trailer.text = getText(R.string.movie_trailer)
                upload_photo.text = getText(R.string.upload_photo)

                val trailerList = movie.trailers.split("=")
                trailerId = trailerList[1]
            }
        })
    }
}