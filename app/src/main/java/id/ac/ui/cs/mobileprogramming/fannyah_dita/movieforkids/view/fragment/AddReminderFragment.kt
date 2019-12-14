package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import kotlinx.android.synthetic.main.fragment_add_reminder.*
import java.util.*

@Suppress("IMPLICIT_CAST_TO_ANY")
class AddReminderFragment : Fragment() {

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

            val date = "${reminder_date.dayOfMonth}/${reminder_date.month+1}/${reminder_date.year}"
            val time = "${reminder_time.hour}:${reminder_time.minute}"

            Toast.makeText(context, "$date $time", Toast.LENGTH_LONG).show()

            Navigation.findNavController(it)
                .navigate(AddReminderFragmentDirections.actionToGallery5())
        }
    }

}
