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

    private val notificationChannelId = "10001"
    private val defaultNotificationChannelId = "default"
    private val delay = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        scheduleNotification(getNotification(getString(R.string.notif_welcome)))

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

    private fun scheduleNotification(notification: Notification) {
        val notificationIntent = Intent(this, NotificationHelper::class.java)
        notificationIntent.putExtra(NotificationHelper.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(NotificationHelper.NOTIFICATION, notification)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val futureInMillis = SystemClock.elapsedRealtime() + delay
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent)
    }

    private fun getNotification(content: String): Notification {
        val intent = Intent(this, MovieActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(application)
            .addNextIntent(intent)
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, defaultNotificationChannelId)
        builder.setContentTitle("MovieForKids")
        builder.setContentText(content)
        builder.setSmallIcon(R.drawable.movie_for_kids)
        builder.setAutoCancel(true)
        builder.setChannelId(notificationChannelId)
        builder.setContentIntent(pendingIntent)
        return builder.build()
    }
}
