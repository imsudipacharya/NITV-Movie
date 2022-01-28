package com.sudip.nitvmovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String API_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=80dff2970093b6849866a98cc4bf6f34&language=en-US&page=1";

    List<NitvMovieModel> nitvMovieModelList;
    RecyclerView recyclerView;
    NitvMovieAdapter nitvMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nitvMovieModelList = new ArrayList<>();
        recyclerView = findViewById(R.id.MainActivityMovieItems);

        GetMovie getMovie = new GetMovie();
        getMovie.execute();

    }

    private class GetMovie extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {

            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;

                try {
                    url = new URL(API_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while (data != -1) {
                        current += (char)data;
                        data = isr.read();
                    }
                    return current;
                }catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return current;
        }
        protected  void onPostExecute(String s){
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0 ; i < jsonArray.length(); i++){
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    NitvMovieModel nitvMovieModel = new NitvMovieModel();
                    nitvMovieModel.setOriginal_title(jsonObject1.getString("original_title"));
                    nitvMovieModel.setAdult(jsonObject1.getString("original_title"));
                    nitvMovieModel.setId(jsonObject1.getInt("id"));
                    nitvMovieModel.setOriginal_language(jsonObject1.getString("original_language"));
                    nitvMovieModel.setOverview(jsonObject1.getString("overview"));
                    nitvMovieModel.setRelease_date(jsonObject1.getString("release_date"));
                    nitvMovieModel.setVote_average(jsonObject1.getInt("vote_average"));
                    nitvMovieModel.setVote_count(jsonObject1.getInt("vote_count"));
                    nitvMovieModel.setBackdrop_path(jsonObject1.getString("backdrop_path"));
                    nitvMovieModel.setPoster_path(jsonObject1.getString("poster_path"));

                    nitvMovieModelList.add(nitvMovieModel);

                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            PutDataIntoRecyclerView(nitvMovieModelList);
        }


    }
    private void PutDataIntoRecyclerView(List<NitvMovieModel> nitvMovieModelList) {
        nitvMovieAdapter = new NitvMovieAdapter(this, nitvMovieModelList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(nitvMovieAdapter);
        nitvMovieAdapter.notifyDataSetChanged();
    }
    }