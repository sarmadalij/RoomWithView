package Repository;


// Note that in order to unit test the PersonRepository, you have to remove the Application
// dependency. This adds complexity and much more code, and this sample is not about testing.

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import DAO.Person;
import DAO.PersonDao;
import DB.RoomPerson;

public class PersonRepository {

    private PersonDao personDao;
    private LiveData<List<Person>> personAllData;

    public PersonRepository(Application application){

        RoomPerson db = RoomPerson.getDatabase(application);
        personDao = db.personDao();
        personAllData = personDao.getOrderedData();

    }

    public LiveData<List<Person>> getPersonAllData(){
        return personAllData;
    }

    public void insert(Person person){
        RoomPerson.databaseWriteExecutor.execute(()->{
            personDao.insert(person);
        });
    }
}
