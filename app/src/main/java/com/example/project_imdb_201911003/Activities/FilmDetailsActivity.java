package com.example.project_imdb_201911003.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_imdb_201911003.DB.DB;
import com.example.project_imdb_201911003.Entity.MovieDetail;
import com.example.project_imdb_201911003.Entity.MovieDetailResult;
import com.example.project_imdb_201911003.R;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FilmDetailsActivity extends AppCompatActivity {
    private volatile MovieDetailResult movieDetailItem;
    String imdbID;
    private ImageView detailed_film_image;
    private TextView detailed_movie_film_name;


    private Button SaveWatchlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_details);
        Bundle bundle=getIntent().getExtras();

        detailed_film_image=findViewById(R.id.detailed_film_image);



        imdbID="";

        if(bundle!=null)
        {
            imdbID= bundle.getString("imdb_id");
        }

        getDetailDataFromNetwork(imdbID);
        while(movieDetailItem==null);


        MovieDetailResult temp=new MovieDetailResult(movieDetailItem.getImdbID(),movieDetailItem.getTitle());
        SaveWatchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDatabase(temp);
            }
        });

        movieDetailItem=null;

    }

    private void saveDataToDatabase(MovieDetailResult temp) {
    }


    private void getDetailDataFromNetwork(String imdbID)
    {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType;
        mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://api.collectapi.com/imdb/imdbSearchById?movieId="+imdbID)
                .method("GET", null)
                .addHeader("authorization", "apikey 3kJbsvpmSBgy1btYqFZS6X:4MntNlBbpHRVoVLHFCrEHD")
                .addHeader("content-type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e)
            {
                Log.d(TAG,"on Failure");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
            {
                if(response.isSuccessful())
                {
                    final String responseBody2 = response.body().string();
                    MovieDetail movieDetail = new Gson().fromJson(responseBody2,MovieDetail.class);
                    setConstructor(movieDetail.getMovieDetailResult());

                }

            }
        });
    }

    private void setConstructor(MovieDetailResult movieDetailResult) {
        this.movieDetailItem=movieDetailResult;
    }

}
}