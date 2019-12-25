package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
            buttonStart.isEnabled = false
        }

        if(isMyServiceRunning(MusicService::class.java)) {
            Log.d("serviceMusic",(isMyServiceRunning((MusicService::class.java)).toString()))
            buttonStart.isEnabled = false
        } else {
            Log.d("serviceMusic",(isMyServiceRunning((MusicService::class.java)).toString()))
        }

        buttonStop.setOnClickListener {
            stopService(Intent(this, MusicService::class.java))
            buttonStart.isEnabled = true
        }
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}