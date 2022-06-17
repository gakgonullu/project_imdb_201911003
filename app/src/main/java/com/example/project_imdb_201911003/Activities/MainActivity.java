package com.example.project_imdb_201911003.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project_imdb_201911003.R;

public class MainActivity extends AppCompatActivity {

    private EditText film_input;
    private Button show_data_btn;
    private Button show_saved_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        film_input = findViewById(R.id.film_input);
        show_data_btn = findViewById(R.id.show_data_btn);
        show_saved_btn = findViewById(R.id.show_saved_btn);

        show_saved_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateRecordedFilmActivity();
            }
        });

        show_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFilmNameToLocalDataSource(film_input.getText().toString());
                navigateFilmListActivity();

            }
        });

    }

    String filmName = getFilmNameFromLocalDataSource();

    private void saveFilmNameToLocalDataSource(String filmName)
    {
        //FilmName bilgisi lokal data source save
        String CONST_DATA = "FILM_NAME";
        SharedPreferences preferences = this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CONST_DATA, String.valueOf(filmName));
        editor.apply();

    }



    private String getFilmNameFromLocalDataSource()
    {
        String result;
        String CONST_DATA= "FILM_NAME";
        SharedPreferences preferences=this.getSharedPreferences(CONST_DATA, getApplicationContext().MODE_PRIVATE);
        result = preferences.getString(CONST_DATA, "");

        return result;

    }


    private void navigateRecordedFilmActivity()
    {
        Intent recordedFilmIntent = new Intent(MainActivity.this, RecordedFilmActivity.class);
        startActivity(recordedFilmIntent);
    }



    private void navigateFilmListActivity()
    {
        Intent filmListIntent = new Intent(MainActivity.this, FilmListActivity.class);
        filmListIntent.putExtra("film_name", filmName);
        startActivity(filmListIntent);
    }

}