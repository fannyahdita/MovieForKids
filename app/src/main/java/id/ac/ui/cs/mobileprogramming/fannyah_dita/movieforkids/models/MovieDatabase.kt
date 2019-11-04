package id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.models

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.fannyah_dita.movieforkids.R

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java, "movie_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return INSTANCE
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(INSTANCE)
                    .execute()
            }
        }
    }
}

class PopulateDbAsyncTask(db: MovieDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val movieDao = db?.movieDao()

    override fun doInBackground(vararg p0: Unit?) {
        movieDao?.insert(
            Movie(
                posterRes = R.drawable.abominable,
                title = "Abominable",
                year = "2019",
                director = "Jill Culton",
                synopsis = "Yi (Chloe Bennet) never meets, she will meet Yeti on the roof of her apartment building in Shanghai. Together with friends, Jin (Tenzing Norgay Trainor) and Peng (Albert Tsai), they named this young Yeti Everest. The exciting adventure begins when Yi and his friends manage to recover, however, their journey will not be easy because Burnish (Eddie Izzard), a wealthy man who has ever tried Everest.",
                trailers = "https://www.youtube.com/watch?v=Ap0NRJD-2ts"
            )
        )

        movieDao?.insert(
            Movie(
                posterRes = R.drawable.secret_life_of_pets_2,
                title = "Secret Life of Pets 2",
                year = "2019",
                director = "Jill Culton",
                synopsis = "Yi (Chloe Bennet) never meets, she will meet Yeti on the roof of her apartment building in Shanghai. Together with friends, Jin (Tenzing Norgay Trainor) and Peng (Albert Tsai), they named this young Yeti Everest. The exciting adventure begins when Yi and his friends manage to recover, however, their journey will not be easy because Burnish (Eddie Izzard), a wealthy man who has ever tried Everest.",
                trailers = "https://www.youtube.com/watch?v=Ap0NRJD-2ts"
            )
        )

        movieDao?.insert(
            Movie(
                posterRes = R.drawable.the_lion_king,
                title = "The Lion King",
                year = "2019",
                director = "Jon Favreu",
                synopsis = "Simba had to run away and save herself from the trap of her uncle, Scar, who was secretly eyeing her father\\'s throne. As an adult, with the help of Timon and Pumbaa, he returned to seize his rights.",
                trailers = "https://www.youtube.com/watch?v=7TavVZMewpY"
            )
        )

        movieDao?.insert(
            Movie(
                posterRes = R.drawable.toy_story_4,
                title = "Toy Story 4",
                year = "2019",
                director = "Josh Cooley",
                synopsis = "Woody always feels confident about his position in the world, and his priority is to look after his child, be it Andy or Bonnie. But when Bonnie adds a new toy named Forky in her toy room, a trip with old and new friends will bring Woody to witness how big the world is for a toy like her.",
                trailers = "https://www.youtube.com/watch?v=wmiIUN-7qhE"
            )
        )
    }
}