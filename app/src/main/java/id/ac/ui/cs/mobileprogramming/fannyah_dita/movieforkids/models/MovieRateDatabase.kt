package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieRate::class], version = 1)
abstract class MovieRateDatabase : RoomDatabase() {
    abstract fun movieRateDao(): MovieRateDao

    companion object {
        private var INSTANCE: MovieRateDatabase? = null

        fun getInstance(context: Context): MovieRateDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieRateDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieRateDatabase::class.java, "rate_database"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}