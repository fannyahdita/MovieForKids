package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.MovieRateViewModel
import kotlinx.android.synthetic.main.fragment_add_rating.*
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.MovieRate

class AddRatingFragment : Fragment() {

    private lateinit var viewModel: MovieRateViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_rating, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MovieRateViewModel::class.java)

        button_submit_rating.setOnClickListener {
            if(rate_edittext.text.toString().trim().isBlank() ||
                    movie_edittext.text.toString().trim().isBlank()) {
                Toast.makeText(activity, getString(R.string.submit_rating_toast), Toast.LENGTH_LONG).show()
            } else {
                addRatingToDb()
                Navigation.findNavController(it)
                    .navigate(AddRatingFragmentDirections.actionToGallery4())
            }
        }
    }

    private fun addRatingToDb() {
        val rating = MovieRate(
            movie_edittext.text.toString(),
            Integer.parseInt(rate_edittext.text.toString())
        )

        viewModel.insert(rating)
    }
}