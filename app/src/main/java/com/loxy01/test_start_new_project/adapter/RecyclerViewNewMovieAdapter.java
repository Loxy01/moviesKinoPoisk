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

public class RecyclerViewNewMovieAdapter extends RecyclerView.Adapter<RecyclerViewNewMovieAdapter.NewMovieViewHolder> {

    private ArrayList<Doc> docsArrayList;
    private static HomeFragment listener;

    public RecyclerViewNewMovieAdapter(ArrayList<Doc> docsArrayList) {
        this.docsArrayList = docsArrayList;
    }
    public interface OnItemClickListener {
        void onNewItemClick(int position);
    }
    public void setOnNewMovieItemClickListener(HomeFragment listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new NewMovieViewHolder(inflater.inflate(R.layout.new_movie_layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewMovieViewHolder holder, int position) {
        Picasso.get().load(docsArrayList.get(position).getPoster().getUrl()).into(holder.movieNewPoster);
    }

    @Override
    public int getItemCount() {
        return docsArrayList.size();
    }

    public class NewMovieViewHolder extends RecyclerView.ViewHolder {
        ImageView movieNewPoster;

        public NewMovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieNewPoster = itemView.findViewById(R.id.MovieNewPoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onNewItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
