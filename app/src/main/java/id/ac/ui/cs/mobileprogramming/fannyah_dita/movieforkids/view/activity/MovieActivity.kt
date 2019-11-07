package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.helper.LocaleHelper
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.helper.NotificationHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        button_change_lang.setOnClickListener {
            if (LocaleHelper.getLanguage(this@MovieActivity) == "en") {
                changeLang("in")
            } else {
                changeLang("en")
            }
        }
    }

    private fun changeLang(lang: String) {
        val config = resources.configuration
        val locale = Locale(lang)
        Locale.setDefault(locale)
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
