package com.example.lastfm.topartists.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lastfm.R;
import com.example.lastfm.core.models.data.Artist;
import com.example.lastfm.core.models.data.Artists;
import com.example.lastfm.core.models.data.ImageItem;
import com.squareup.picasso.Picasso;

public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.ViewHolder> {
    private Artists artists = new Artists();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View artistItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new TopArtistsAdapter.ViewHolder(artistItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setArtist(artists.getArtist().get(position));
    }

    @Override
    public int getItemCount() {
        if (artists != null) {
            return artists.getArtist().size();
        }
        return 0;
    }

    public void setItems(Artists artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView playCountTextView;
        private TextView artistNameTextView;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            artistNameTextView = itemView.findViewById(R.id.artist);
            playCountTextView = itemView.findViewById(R.id.artist_playCount);
            imageView = itemView.findViewById(R.id.image_artist);
        }

        public void setArtist(Artist artist) {
            setArtistName(artist.getName());
            setPlayCount(artist.getPlayCount());
            if (artist.getImage() != null && artist.getImage().size() > 0) {
                for (ImageItem img : artist.getImage()) {
                    if (img.getSize().equalsIgnoreCase("large")) {
                        setImage(img.getText());
                    }
                }
            }
        }

        private void setArtistName(String artistName) {
            artistNameTextView.setText(artistName);
        }

        private void setPlayCount(String playCount) {
            playCountTextView.setText(playCount);
        }

        private void setImage(String imageUrl) {
            Picasso.get().load(imageUrl).into(imageView);
        }
    }
}
