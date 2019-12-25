package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.helper.NotificationHelper


class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private val notificationChannelId = "10001"
    private val defaultNotificationChannelId = "default"
    private val listOfNotif = listOf("Don't you think The Lion King is a good movie to watch with your little one?",
        "Do you know that Toy Story 4 has earned 1,073 billion USD? WOW! Click here to watch the trailer",
        "Abominable is a movie about Yeti. Do you think it's real? Click here to watch the trailer")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val index = (0 until 2).random()
        scheduleNotification(getNotification(listOfNotif[index]))

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MovieActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
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

        val futureInMillis = SystemClock.elapsedRealtime() + 60000
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
        builder.setStyle(NotificationCompat.BigTextStyle().bigText(content))
        builder.setSmallIcon(R.drawable.movie_for_kids)
        builder.setAutoCancel(true)
        builder.setChannelId(notificationChannelId)
        builder.setContentIntent(pendingIntent)
        return builder.build()
    }
}