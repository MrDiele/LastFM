package com.example.lastfm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lastfm.R;
import com.example.lastfm.models.ImageItem;
import com.example.lastfm.models.TopTracksResponse;
import com.example.lastfm.models.Track;
import com.squareup.picasso.Picasso;

public class TopTracksAdapter extends RecyclerView.Adapter<TopTracksAdapter.ViewHolder> {
    private TopTracksResponse tracks = new TopTracksResponse();

    @NonNull
    @Override
    public TopTracksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View songItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new TopTracksAdapter.ViewHolder(songItem);
    }

    @Override
    public void onBindViewHolder(@NonNull TopTracksAdapter.ViewHolder holder, int position) {
        holder.setTrack(tracks.getTracks().getTrack().get(position));
    }

    @Override
    public int getItemCount() {
        if (tracks.getTracks() != null) {
            return tracks.getTracks().getTrack().size();
        }
        return 0;
    }

    public void setItems(TopTracksResponse tracks) {
        this.tracks = tracks;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView songNameTextView;
        private TextView artistNameTextView;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            songNameTextView = itemView.findViewById(R.id.song_name);
            artistNameTextView = itemView.findViewById(R.id.artist_name);
            imageView = itemView.findViewById(R.id.image_track);
        }

        public void setTrack(Track track) {

            setName(track.getName());
            setArtistName(track.getArtist().getName());
            if (track.getImage() != null && track.getImage().size() > 0) {
                for (ImageItem img : track.getImage()) {
                    if (img.getSize().equalsIgnoreCase("large")) {
                        setImage(img.getText());
                    }
                }
            }
        }

        private void setName(String songName) {
            songNameTextView.setText(songName);
        }

        private void setArtistName(String artistName) {
            artistNameTextView.setText(artistName);
            artistNameTextView.setText(artistName);
        }

        private void setImage(String imageUrl) {
            Picasso.get().load(imageUrl).into(imageView);
        }
    }
}
