package com.example.paezand.movieslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paezand.movieslist.http.apimodel.Result;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by paezand on 10/3/17.
 */

public class CustomAdapter extends ArrayAdapter<Result> {

    public CustomAdapter(@NonNull final Context context, ArrayList<Result> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(final int position,
                        @Nullable View convertView,
                        @NonNull final ViewGroup parent) {
        Result movie = (Result) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }


        TextView movieName = (TextView) convertView.findViewById(R.id.movie_name);
        TextView movieYear = (TextView) convertView.findViewById(R.id.movie_year);
        TextView movieDescription = (TextView) convertView.findViewById(R.id.movie_description);

        movieName.setText(movie.getTitle());
        movieYear.setText(movie.getReleaseDate());
        movieDescription.setText(movie.getOverview());

        return convertView;
    }
}
