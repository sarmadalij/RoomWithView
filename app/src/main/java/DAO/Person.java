package DAO;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_detail")
public class Person {

    // Empty constructor for Room
    public Person() {
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "person_name")
    private String personName;

    @NonNull
    @ColumnInfo(name = "person_profession")
    private String personProfession;


    //constructor
    public Person(@NonNull String name, @NonNull String profession){

        this.personName = name;
        this.personProfession = profession;
    }

    //getters
    public String getPersonName(){
        return this.personName;
    }

    public String getPersonProfession(){
        return this.personProfession;
    }

    //setters


    public void setPersonName(@NonNull String personName) {
        this.personName = personName;
    }

    public void setPersonProfession(@NonNull String personProfession) {
        this.personProfession = personProfession;
    }
}
