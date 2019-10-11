package com.example.lastfm.toptracks.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lastfm.R;
import com.example.lastfm.core.models.data.ImageItem;
import com.example.lastfm.core.models.data.Track;
import com.example.lastfm.core.models.data.Tracks;
import com.example.lastfm.presentation.view.InfoClickListener;
import com.squareup.picasso.Picasso;

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.ViewHolder> {
    private final InfoClickListener infoClickListener;
    private Tracks tracks = new Tracks();

    public TopTracksAdapter(InfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    @NonNull
    @Override
    public TopTracksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View songItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new ViewHolder(songItem, infoClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopTracksAdapter.ViewHolder holder, int position) {
        holder.setTrack(tracks.getTrack().get(position), position);
    }

    @Override
    public int getItemCount() {
        if (tracks != null) {
            return tracks.getTrack().size();
        }
        return 0;
    }

    public void setItems(Tracks tracks) {
        this.tracks = tracks;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final InfoClickListener infoClickListener;
        private TextView positionTextView;
        private TextView songNameTextView;
        private TextView artistNameTextView;
        private ImageView imageView;

        ViewHolder(View itemView, InfoClickListener infoClickListener) {
            super(itemView);
            positionTextView = itemView.findViewById(R.id.position_track);
            songNameTextView = itemView.findViewById(R.id.song_name);
            artistNameTextView = itemView.findViewById(R.id.artist_name);
            imageView = itemView.findViewById(R.id.image_track);
            this.infoClickListener = infoClickListener;
        }

        void setTrack(Track track, int position) {
            setPosition(position + 1);

            if (track.getImage() != null && track.getImage().size() > 0) {
                for (ImageItem img : track.getImage()) {
                    if (img.getSize().equalsIgnoreCase("large")) {
                        setImage(img.getText());
                        itemView.setOnClickListener((v) -> infoClickListener.onClick(img.getText(), track.getName()));
                    }
                }
            }

            setTrackName(track.getName());
            setArtistName(track.getArtist().getName());
        }

        private void setPosition(int position) {
            positionTextView.setText(String.valueOf(position));
        }

        private void setImage(String imageUrl) {
            Picasso.get().load(imageUrl).into(imageView);
        }

        private void setTrackName(String songName) {
            songNameTextView.setText(songName);
        }

        private void setArtistName(String artistName) {
            artistNameTextView.setText(artistName);
        }
    }
}
