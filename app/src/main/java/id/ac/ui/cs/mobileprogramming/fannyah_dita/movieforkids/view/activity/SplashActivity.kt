package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R


class SplashActivity : AppCompatActivity() {
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MovieActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}