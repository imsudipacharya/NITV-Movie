package com.sudip.nitvmovie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NitvMovieDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Intent myIntent = getIntent();
        int id = myIntent.getIntExtra("id", 0);
        String original_title = myIntent.getStringExtra("original_title");
        String original_language = myIntent.getStringExtra("original_language");
        String overview = myIntent.getStringExtra("overview");
        String poster_path = myIntent.getStringExtra("poster_path");
        String release_date = myIntent.getStringExtra("release_date");
        String backdrop_path = myIntent.getStringExtra("backdrop_path");
        String isadult = myIntent.getStringExtra("isadult");
        int vote_count = myIntent.getIntExtra("vote_count", 0);
        int vote_average = myIntent.getIntExtra("vote_average", 0);

        ImageView moviePoster = (ImageView) findViewById(R.id.MovieDetailVideoPoster);
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + poster_path)
                .placeholder(R.drawable.gif_loading_placeholder)
                .fit()
                .into(moviePoster);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + backdrop_path)
                .placeholder(R.drawable.gif_loading_placeholder)
                .fit()
                .into((ImageView) findViewById(R.id.MovieDetailMovieImage));

        TextView movieTitle = findViewById(R.id.MovieDetailMovieTitle);
        movieTitle.setText(original_title);

        if (isadult.equals("true")) {
            Picasso.get()
                    .load(R.drawable.image_adult_symbol)
                    .into((ImageView) findViewById(R.id.MovieDetailForAdult));
        } else {
            Picasso.get()
                    .load(R.drawable.image_no_adult_symbol)
                    .into((ImageView) findViewById(R.id.MovieDetailForAdult));
        }

        TextView movieOverView = findViewById(R.id.MovieDetailOverView);
        movieOverView.setText(overview);

        ImageButton playbutton = findViewById(R.id.MovieDetailVideoPlayButton);
        VideoView video = findViewById(R.id.MainMovieDetailVideo);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.setVisibility(View.VISIBLE);
                moviePoster.setVisibility(View.GONE);
                MediaController mediaController = new MediaController(NitvMovieDetail.this);
                mediaController.setAnchorView(video);
                video.setMediaController(mediaController);
                video.requestFocus();
                String uri
                        = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1";
                video.setVideoURI(Uri.parse(uri));
                video.start();
            }
        });

        TextView releasedDate = findViewById(R.id.MovieDetailReleaseDate);
        releasedDate.setText("Released On :" + release_date);

        TextView totalVoting = findViewById(R.id.MovieDetailTotalVoting);
        totalVoting.setText("Voting :" + vote_count);


    }
}
