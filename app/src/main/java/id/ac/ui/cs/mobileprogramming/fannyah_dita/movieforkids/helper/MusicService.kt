package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.helper

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R

class MusicService : Service() {

    private lateinit var mediaPlayer : MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        mediaPlayer = MediaPlayer.create(this, R.raw.hakunamatata)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }
}