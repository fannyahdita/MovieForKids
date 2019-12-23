package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.helper.NotificationHelper
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity.MovieActivity
import kotlinx.android.synthetic.main.fragment_add_reminder.*
import java.util.*


class AddReminderFragment : Fragment() {

    val NOTIFICATION_CHANNEL_ID = "10001"
    private val default_notification_channel_id = "default"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_reminder, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        reminder_time.setIs24HourView(true)

        button_submit_reminder.setOnClickListener {

            val movieTitle = reminder_movie_name.text

//            val date = reminder_date.dayOfMonth
//            val month = reminder_date.dayOfMonth + 1
//            val year = reminder_date.year
//            val hour = reminder_time.hour
//            val min = reminder_time.minute

            val date =
                "${reminder_date.dayOfMonth}/${reminder_date.month + 1}/${reminder_date.year}"
            val time = "${reminder_time.hour}:${reminder_time.minute}"

            scheduleNotification(getNotification(date, time, movieTitle.toString()), date, time)

            Toast.makeText(context, "Set alarm for $movieTitle at $date $time", Toast.LENGTH_LONG)
                .show()

            Navigation.findNavController(it)
                .navigate(AddReminderFragmentDirections.actionToGallery5())
        }
    }

    private fun scheduleNotification(notification: Notification, date: String, time: String) {
        val notificationIntent = Intent(context, NotificationHelper::class.java)
        notificationIntent.putExtra(NotificationHelper.NOTIFICATION_ID, 1)
        notificationIntent.putExtra(NotificationHelper.NOTIFICATION, notification)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val dateSplit = date.split("/")
        val timeSplit = time.split(":")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.DAY_OF_MONTH, dateSplit[0].toInt())
        calendar.set(Calendar.MONTH, dateSplit[1].toInt())
        calendar.set(Calendar.YEAR, dateSplit[2].toInt())
        calendar.set(Calendar.HOUR, timeSplit[0].toInt())
        calendar.set(Calendar.MINUTE, timeSplit[1].toInt())

        val alarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    private fun getNotification(date: String, time: String, title: String): Notification {
        val intent = Intent(context, MovieActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(requireActivity())
            .addNextIntent(intent)
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = Notification.Builder(context, default_notification_channel_id)
        builder.setContentTitle("Reminder To Watch Movie")
        builder.setContentText("It's time to watch $title at $date $time")
        builder.setSmallIcon(R.drawable.movie_for_kids)
        builder.setAutoCancel(true)
        builder.setChannelId(default_notification_channel_id)
        builder.setContentIntent(pendingIntent)
        return builder.build()

    }

}
