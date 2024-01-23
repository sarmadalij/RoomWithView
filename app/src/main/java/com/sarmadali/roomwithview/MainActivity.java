package com.sarmadali.roomwithview;

import static com.sarmadali.roomwithview.NewPersonActivity.EXTRA_REPLY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import DAO.Person;
import recyclerviewUtils.PersonListAdapter;
import viewModel.PersonViewModel;

public class MainActivity extends AppCompatActivity {

    private PersonViewModel personViewModel;
    public static final int NEW_RECORD_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final PersonListAdapter adapter = new PersonListAdapter(new PersonListAdapter.PersonDIff());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        // Update the cached copy of the words in the adapter.
        personViewModel.getpersonAllData().observe(this, adapter::submitList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewPersonActivity.class);
            startActivityForResult(intent, NEW_RECORD_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_RECORD_REQUEST_CODE && resultCode == RESULT_OK) {
            Person word = new Person(data.getStringExtra("getName"),
                    data.getStringExtra("getProfession"));
            personViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}