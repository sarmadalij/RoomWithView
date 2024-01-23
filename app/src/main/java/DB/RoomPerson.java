package DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import DAO.Person;
import DAO.PersonDao;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
public abstract class RoomPerson extends RoomDatabase {

    public abstract PersonDao personDao();

    private static volatile RoomPerson INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static RoomPerson getDatabase(final Context context){

        if (INSTANCE==null){
            synchronized (RoomPerson.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            RoomPerson.class,"person_detail")
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block

            databaseWriteExecutor.execute(()-> {

                // Populate the database in the background.
                // If you want to start with more data, just add them.

                PersonDao dao = INSTANCE.personDao();
                dao.deleteAll();

                Person person = new Person("Sarmad","Android Developer");
                dao.insert(person);
                person = new Person("Sarmad1","Android Developer1");
                dao.insert(person);
            });
        }
    };
}
