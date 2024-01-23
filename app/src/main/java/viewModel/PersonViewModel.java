package viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import DAO.Person;
import Repository.PersonRepository;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository personRepository;
    private final LiveData<List<Person>> personAllData;

    //constructor
    public PersonViewModel(@NonNull Application application){
        super(application);

        personRepository = new PersonRepository(application);
        personAllData = personRepository.getPersonAllData();

    }

    public LiveData<List<Person>> getpersonAllData(){
        return personAllData;
    }

    public void insert(Person person){
        personRepository.insert(person);
    }


}
