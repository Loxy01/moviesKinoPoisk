package com.loxy01.test_start_new_project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loxy01.test_start_new_project.MainMoviesActivity;
import com.loxy01.test_start_new_project.R;
import com.loxy01.test_start_new_project.model.Doc;
import com.loxy01.test_start_new_project.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewInTrendMovieAdapter extends RecyclerView.Adapter<RecyclerViewInTrendMovieAdapter.MovieViewHolder> {

    private ArrayList<Doc> docsArrayList;
    private static HomeFragment listener;

    public RecyclerViewInTrendMovieAdapter(ArrayList<Doc> docsArrayList) {
        this.docsArrayList = docsArrayList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnInTrendItemClickListener(HomeFragment listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MovieViewHolder(inflater.inflate(R.layout.in_trend_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Picasso.get().load(docsArrayList.get(position).getPoster().getUrl()).into(holder.movieInTrendPoster);
    }

    @Override
    public int getItemCount() {
        return docsArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieInTrendPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieInTrendPoster = itemView.findViewById(R.id.MovieInTrendPoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onInTrendItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
