package com.sudip.nitvmovie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NitvMovieAdapter extends RecyclerView.Adapter<NitvMovieAdapter.MyViewHolder> {

    private Context mContext;
    private List<NitvMovieModel> nitvMovieModelList;

    public NitvMovieAdapter(Context mContext, List<NitvMovieModel> nitvMovieModelList) {
        this.mContext = mContext;
        this.nitvMovieModelList = nitvMovieModelList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.main_movie_item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movieTitle.setText(nitvMovieModelList.get(position).getOriginal_title());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/"+nitvMovieModelList.get(position).getBackdrop_path())
                .placeholder(R.drawable.gif_loading_placeholder)
                .fit()
                .into(holder.movieImage);
        String adult = nitvMovieModelList.get(position).getAdult();
        if (adult.equals("true")){
            Picasso.get()
                    .load(R.drawable.image_adult_symbol)
                    .into((ImageView) holder.itemView.findViewById(R.id.MainMovieItemIsAdult));
        }else{
            Picasso.get()
                    .load(R.drawable.image_no_adult_symbol)
                    .into((ImageView) holder.itemView.findViewById(R.id.MainMovieItemIsAdult));
        }

         String original_language = nitvMovieModelList.get(position).getOriginal_language();
         String overview = nitvMovieModelList.get(position).getOverview();
        String poster_path = nitvMovieModelList.get(position).getPoster_path();
        String backdrop_path = nitvMovieModelList.get(position).getBackdrop_path();;
         String release_date = nitvMovieModelList.get(position).getRelease_date();
         int id = nitvMovieModelList.get(position).getId();
         int vote_count = nitvMovieModelList.get(position).getVote_count();
         float vote_average = nitvMovieModelList.get(position).getVote_average();




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(mContext, NitvMovieDetail.class);
                myIntent.putExtra("original_title",nitvMovieModelList.get(position).getOriginal_title());
                myIntent.putExtra("original_language",original_language);
                myIntent.putExtra("overview",overview);
                myIntent.putExtra("poster_path",poster_path);
                myIntent.putExtra("release_date",release_date);
                myIntent.putExtra("backdrop_path",backdrop_path);
                myIntent.putExtra("isadult",adult);
                myIntent.putExtra("vote_count", vote_count);
                myIntent.putExtra("vote_average", vote_average);
                holder.itemView.getContext().startActivity(myIntent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return nitvMovieModelList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle;
        ImageView movieImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.MainMovieItemMovieName);
            movieImage = itemView.findViewById(R.id.MainMovieItemImageView);

        }
    }
}

