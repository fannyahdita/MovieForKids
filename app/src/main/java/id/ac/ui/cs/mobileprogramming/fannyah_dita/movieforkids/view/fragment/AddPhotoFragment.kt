package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models.KidsPhoto
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.viewmodel.KidsPhotoViewModel
import kotlinx.android.synthetic.main.fragment_add_photo.*

class AddPhotoFragment : Fragment() {

    companion object {
        fun newInstance() = AddPhotoFragment()
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    private lateinit var viewModel: KidsPhotoViewModel
    private var photo = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(KidsPhotoViewModel::class.java)

        button_add_photo.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(
                        context!!,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    button_submit_photo.setBackgroundColor(resources.getColor(R.color.grey))
                    button_submit_photo.isClickable = false
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    ) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                requireActivity(),
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                        ) {

                            val builder = AlertDialog.Builder(context)
                            builder.setMessage(getString(R.string.photo_msg))
                                .setTitle(getString(R.string.photo_title))

                            builder.setPositiveButton("OK") { dialog, id ->
                                ActivityCompat.requestPermissions(
                                    requireActivity(),
                                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                                    PERMISSION_CODE
                                )
                            }

                            val dialog = builder.create()
                            dialog.show()
                        }
                    } else {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            PERMISSION_CODE
                        )
                    }
                } else {
                    pickImageFromGallery()
                    button_submit_photo.setBackgroundColor(resources.getColor(R.color.soft_pink))
                    button_submit_photo.isClickable = true
                    button_add_photo.visibility = View.GONE
                    button_submit_photo.visibility = View.VISIBLE
                    button_change_photo.visibility = View.VISIBLE

                }
            } else {
                pickImageFromGallery()
                button_submit_photo.setBackgroundColor(resources.getColor(R.color.soft_pink))
                button_submit_photo.isClickable = true
                button_add_photo.visibility = View.GONE
                button_submit_photo.visibility = View.VISIBLE
                button_change_photo.visibility = View.VISIBLE

            }
        }

        button_submit_photo.setOnClickListener {
            button_add_photo.visibility = View.GONE
            button_submit_photo.visibility = View.GONE
            button_change_photo.visibility = View.GONE
            button_submit_enable.visibility = View.VISIBLE
            button_submit_disable.visibility = View.GONE

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
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }

        button_submit_enable.setOnClickListener {
            if (movie_edittext.text.toString().trim().isBlank()
                || describe_textview.text.toString().trim().isBlank()
            ) {
                Toast.makeText(activity, getString(R.string.submit_photo_toast), Toast.LENGTH_SHORT)
                    .show()
            }
            else {
                addPhotoToDb()
                Navigation.findNavController(it)
                    .navigate(AddPhotoFragmentDirections.actionToGallery2())
            }
        }

    }

    private fun addPhotoToDb() {
        val newPhoto = KidsPhoto(
            photo,
            desc_edittext.text.toString(),
            movie_edittext.text.toString()
        )
        viewModel.insert(newPhoto)
        Toast.makeText(activity, getString(R.string.photo_saved), Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            photo = (data?.data).toString()
            image_photokids.setImageURI(data?.data)
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
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
}