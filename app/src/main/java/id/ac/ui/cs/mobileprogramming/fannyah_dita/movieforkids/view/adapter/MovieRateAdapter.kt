package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.MovieRate
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import kotlinx.android.synthetic.main.item_rating_list.view.*

class MovieRateAdapter : RecyclerView.Adapter<MovieRateAdapter.ViewHolder>(){

    var rates : List<MovieRate> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rating_list,parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.movie_title_rate.text = rates[position].title
        holder.view.rate_textview.text = "${rates[position].rate}"
    }
    override fun getItemCount(): Int {
        return rates.size
    }

    fun setRating(movieRate: List<MovieRate>) {
        this.rates = movieRate
        notifyDataSetChanged()

    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}