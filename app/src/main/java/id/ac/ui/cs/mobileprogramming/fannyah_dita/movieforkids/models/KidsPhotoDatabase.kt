package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KidsPhoto::class], version = 1)
abstract class KidsPhotoDatabase : RoomDatabase() {

    abstract fun kidsPhotoDao(): KidsPhotoDao

    companion object {
        private var INSTANCE: KidsPhotoDatabase? = null

        fun getInstance(context: Context): KidsPhotoDatabase? {
            if (INSTANCE == null) {
                synchronized(KidsPhotoDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        KidsPhotoDatabase::class.java, "photo_database"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}