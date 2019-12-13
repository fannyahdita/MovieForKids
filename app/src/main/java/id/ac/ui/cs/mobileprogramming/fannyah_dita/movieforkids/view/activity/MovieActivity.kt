package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.helper.LocaleHelper
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

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (!wifiManager.isWifiEnabled) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(getString(R.string.wifi_msg))
                .setTitle(getString(R.string.wifi_title))

            builder.setPositiveButton("ENABLE WIFI") { _, _ ->
                wifiManager.isWifiEnabled = true
            }
            builder.setNegativeButton("I'M GOOD") { _, _ ->

            }
            val dialog = builder.create()
            dialog.show()
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
