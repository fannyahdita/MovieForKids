package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.Movie
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter.MovieAdapter
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.MovieViewModel
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity.MusicActivity
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {
    companion object {
        fun getInstance() = MovieListFragment()
    }

    init {
        System.loadLibrary("movieforkids")
    }

    private lateinit var viewModel: MovieViewModel
    private val movieAdapter = MovieAdapter()
    external fun randommov(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        val indexRandomMovie = randommov()
        Log.wtf("RAND : ", indexRandomMovie.toString())

        showMovieRecommendation(indexRandomMovie)

        movies_recyclerview.layoutManager = LinearLayoutManager(context)
        movies_recyclerview.adapter = movieAdapter

        button_gallery.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(MovieListFragmentDirections.actionToGallery())
        }

        button_music.setOnClickListener {
            val intent = Intent(activity, MusicActivity::class.java)
            startActivity(intent)
        }

        button_add_reminder.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(MovieListFragmentDirections.actionToAddReminder())
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.getAllMovies().observe(this,
            Observer<List<Movie>> { list ->
                movieAdapter.setMovies(list)
            }
        )
    }

    private fun showMovieRecommendation(index: Int) {

        viewModel.detailMovie(index).observe(this, Observer {

            if(it != null) {
                val builder = AlertDialog.Builder(context)
                builder.setMessage(it.title).setTitle("What should we watch today?")

                builder.setPositiveButton("OK") { _, _ -> }


                val dialog = builder.create()
                dialog.show()
            }


        })

    }
}