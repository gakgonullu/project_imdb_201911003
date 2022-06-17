package com.example.project_imdb_201911003.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project_imdb_201911003.Adapter.DBAdapter;
import com.example.project_imdb_201911003.DB.DB;
import com.example.project_imdb_201911003.Entity.MovieDetailResult;
import com.example.project_imdb_201911003.R;
import com.google.android.gms.ads.mediation.Adapter;

import java.util.ArrayList;

public class RecordedFilmActivity extends AppCompatActivity {

    private RecyclerView savedFilmList;
    public ArrayList<MovieDetailResult> movieDetailResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded_film);
        savedFilmList = findViewById(R.id.saved_film_list_recyclerview);
        movieDetailResultList= DB.getInstance(this).getFilmList();

        new RecordedFilmActivity().this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setAdapterToRecyclerView(movieDetailResultList);
            }
        });
    }

    private void setAdapterToRecyclerView(ArrayList<MovieDetailResult> movieDetails) {
        DB adapter = new DBAdapter(movieDetails);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        savedFilmList.setLayoutManager(mLayoutManager);
        savedFilmList.setAdapter(Adapter);
    }
}