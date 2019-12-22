package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.helper.MusicService
import kotlinx.android.synthetic.main.activity_music.*


class MusicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        buttonStart.setOnClickListener {
            startService(Intent(this, MusicService::class.java))
        }

        buttonStop.setOnClickListener {
            stopService(Intent(this, MusicService::class.java))
        }
    }
}