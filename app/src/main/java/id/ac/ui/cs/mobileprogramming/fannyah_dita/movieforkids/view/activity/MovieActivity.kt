package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.view.activity

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.helper.LocaleHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MovieActivity : AppCompatActivity() {

    private lateinit var locale : Locale

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
