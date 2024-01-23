package com.sarmadali.roomwithview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewPersonActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText name;
    private EditText profession;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_person);

        name = findViewById(R.id.name);
        profession = findViewById(R.id.profession);

        button = findViewById(R.id.button_save);

        button.setOnClickListener(view -> {

            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(name.getText()) && TextUtils.isEmpty(profession.getText())){

                setResult(RESULT_CANCELED, replyIntent);
            }else {

                String word = name.getText().toString();
                String word1 = profession.getText().toString();

                replyIntent.putExtra("getName", word);
                replyIntent.putExtra("getProfession", word1);

                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}