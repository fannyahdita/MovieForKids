package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.os.Bundle
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
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {
    companion object {
        fun getInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieViewModel
    private val movieAdapter = MovieAdapter()

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

        movies_recyclerview.layoutManager = LinearLayoutManager(context)
        movies_recyclerview.adapter = movieAdapter

        button_gallery.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(MovieListFragmentDirections.actionToGallery())
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
}