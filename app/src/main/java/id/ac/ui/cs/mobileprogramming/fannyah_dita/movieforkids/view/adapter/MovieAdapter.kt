package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.Movie
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment.MovieListFragmentDirections
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

    private var movie: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movie[position]
        holder.view.movie_image_list.setImageResource(currentMovie.posterRes)
        holder.view.movie_title_list.text = currentMovie.title
        holder.view.movie_year_list.text = currentMovie.year
        holder.view.setOnClickListener{
            Navigation.findNavController(it)
                .navigate(MovieListFragmentDirections.actionToDetailFragment().setId(position))
        }
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    fun setMovies(movie: List<Movie>) {
        this.movie = movie
        notifyDataSetChanged()
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}
