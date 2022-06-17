package com.example.project_imdb_201911003.Entity;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieDetailResult implements Parcelable{

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;

    protected MovieDetailResult(Parcel in) {
        title = in.readString();
        year = in.readString();
        imdbID = in.readString();
        type = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(year);
        dest.writeString(imdbID);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieDetailResult> CREATOR = new Creator<MovieDetailResult>() {
        @Override
        public MovieDetailResult createFromParcel(Parcel in) {
            return new MovieDetailResult(in);
        }

        @Override
        public MovieDetailResult[] newArray(int size) {
            return new MovieDetailResult[size];
        }
    };
}
