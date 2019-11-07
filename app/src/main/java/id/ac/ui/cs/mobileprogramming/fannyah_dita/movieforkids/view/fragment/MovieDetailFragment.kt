package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeStandalonePlayer
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
    }

    private lateinit var movieViewModel: MovieViewModel
    private var trailerId = ""
    private var movieId = 0
    private var apiKey = "API-KEY"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            movieId = MovieDetailFragmentArgs.fromBundle(it).id
        }

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        observeViewModel()

        thumbnail_youtube_container.setOnClickListener {
            val intent = YouTubeStandalonePlayer.createVideoIntent(activity, apiKey, trailerId)
            startActivity(intent)
        }
    }

    private fun observeViewModel() {

        movieViewModel.detailMovie(movieId + 1).observe(this, Observer { movie ->
            movie?.let {
                movie_title.text = movie.title.capitalize()
                movie_poster.setImageResource(movie.posterRes)
                movie_year.text = Html.fromHtml(getString(R.string.movie_year, movie.year))
                movie_director.text =
                    Html.fromHtml(getString(R.string.movie_director, movie.director))
                movie_synopsis.text =
                    Html.fromHtml(getString(R.string.movie_synopsis, movie.synopsis))
                movie_trailer.text = getText(R.string.movie_trailer)
                trailerId = movie.trailerId
                setYoutubeThumbnail(movie.trailerId)
            }
        })
    }

    private fun setYoutubeThumbnail(trailerId: String) {
        val imgUrl = "https://img.youtube.com/vi/$trailerId/0.jpg"
        Glide.with(this)
            .load(imgUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(imageview_thumbnail)
    }

}