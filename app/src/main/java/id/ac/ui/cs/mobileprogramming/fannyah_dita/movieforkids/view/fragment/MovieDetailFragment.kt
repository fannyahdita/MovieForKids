package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeStandalonePlayer
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.KidsPhoto
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.KidsPhotoViewModel
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MovieDetailFragment()
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var kidsPhotoViewModel: KidsPhotoViewModel
    private var trailerId = ""
    private var movieId = 0
    private var apiKey = "API_KEY"
    private var photo = ""

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
        kidsPhotoViewModel = ViewModelProviders.of(this).get(KidsPhotoViewModel::class.java)
        observeViewModel()

//        val imageUrl = "http://img.youtube.com/vi/$trailerId/0.jpg"
        Glide.with(this)
            .load("http://img.youtube.com/vi/wmiIUN-7qhE/hqdefault.jpg")
            .placeholder(R.drawable.ic_placeholder)
            .into(imageview_thumbnail)

        imageview_thumbnail.setOnClickListener {
            val intent = YouTubeStandalonePlayer.createVideoIntent(activity, apiKey, trailerId)
            startActivity(intent)
        }

        button_photo.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity?.applicationContext?.let { activity ->
                        checkSelfPermission(
                            activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    }
                    != PackageManager.PERMISSION_GRANTED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }

            button_photo.visibility = View.GONE
            button_submit_photo.visibility = View.VISIBLE
            button_change_photo.visibility = View.VISIBLE
        }

        button_submit_photo.setOnClickListener {
            addPhotoToDb()
            button_photo.visibility = View.GONE
            button_submit_photo.visibility = View.GONE
            button_change_photo.visibility = View.GONE
        }

        button_change_photo.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity?.applicationContext?.let { activity ->
                        checkSelfPermission(
                            activity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                    }
                    != PackageManager.PERMISSION_GRANTED) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }
    }

    private fun observeViewModel() {

        movieViewModel.detailMovie(movieId + 1).observe(this, Observer { movie ->
            movie?.let {
                movie_title.text = movie.title
                movie_poster.setImageResource(movie.posterRes)
//                imageview_thumbnail.setImageResource(movie.posterRes)
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

//        kidsPhotoViewModel.getPhoto(photoId+1).observe(this, Observer {kidsPhoto ->
//            kidsPhoto?.let {
//                if (photo != "") {
//                    val uri = Uri.parse(photo)
//                    image_photokids.setImageURI(uri)
//                }
//            }
//        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(
                        activity,
                        getString(R.string.photo_permission_denied),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun addPhotoToDb() {
        val newPhoto = KidsPhoto(photo)
        kidsPhotoViewModel.insert(newPhoto)
        Toast.makeText(activity, getString(R.string.photo_saved), Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            photo = (data?.data).toString()
            image_photokids.setImageURI(data?.data)
        }
    }
}