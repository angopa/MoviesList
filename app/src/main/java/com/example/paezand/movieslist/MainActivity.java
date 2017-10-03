package com.example.paezand.movieslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.paezand.movieslist.http.BaseApi;
import com.example.paezand.movieslist.http.apimodel.Result;
import com.example.paezand.movieslist.http.apimodel.TopMovies;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private BaseApi baseApi;

    private ArrayAdapter<String> arrayAdapter;

    private List<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        baseApi = BaseApi.getInstance();

        getTopMovieList();

        ListView movieContainer = findViewById(R.id.movie_list_container);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        movieContainer.setAdapter(arrayAdapter);
    }

    private void getTopMovieList() {
        baseApi.getMovieList(1, new Callback<TopMovies>() {
            @Override
            public void onResponse(Call<TopMovies> call, Response<TopMovies> response) {
                Log.d(TAG, "onResponse");
                if (response.body() != null) {
                    for (Result result : response.body().getResults()) {
                        items.add(result.getTitle());
                    }
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TopMovies> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });
    }



}
