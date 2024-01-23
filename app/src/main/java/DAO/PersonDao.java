package DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  PersonDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy


    //data insertion
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Person person);

    //data deletion
    @Query("DELETE FROM person_detail")
    void deleteAll();

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.

    @Query("SELECT * FROM person_detail ORDER BY person_name ASC")
    LiveData<List<Person>> getOrderedData();
}
