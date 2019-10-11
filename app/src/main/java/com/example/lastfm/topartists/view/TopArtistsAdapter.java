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
import com.example.lastfm.presentation.view.InfoClickListener;
import com.squareup.picasso.Picasso;

public class TopArtistsAdapter extends RecyclerView.Adapter<TopArtistsAdapter.ViewHolder> {
    private final InfoClickListener infoClickListener;
    private Artists artists = new Artists();

    public TopArtistsAdapter(InfoClickListener infoClickListener) {
        this.infoClickListener = infoClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View artistItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new ViewHolder(artistItem, infoClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TopArtistsAdapter.ViewHolder holder, int position) {
        holder.setArtist(artists.getArtists().get(position), position);
    }

    @Override
    public int getItemCount() {
        if (artists != null) {
            return artists.getArtists().size();
        }
        return 0;
    }

    public void setItems(Artists artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final InfoClickListener infoClickListener;
        private TextView positionTextView;
        private ImageView imageView;
        private TextView artistNameTextView;
        private TextView listenersTextView;


        ViewHolder(View itemView, InfoClickListener infoClickListener) {
            super(itemView);
            positionTextView = itemView.findViewById(R.id.position_artist);
            imageView = itemView.findViewById(R.id.image_artist);
            artistNameTextView = itemView.findViewById(R.id.artist_name);
            listenersTextView = itemView.findViewById(R.id.artist_listeners);
            this.infoClickListener = infoClickListener;
        }

        void setArtist(Artist artist, int position) {
            setPositionTextView(position + 1);

            if (artist.getImage() != null && artist.getImage().size() > 0) {
                for (ImageItem img : artist.getImage()) {
                    if (img.getSize().equalsIgnoreCase("large")) {
                        setImage(img.getText());
                        itemView.setOnClickListener((v) -> infoClickListener.onClick(img.getText(), artist.getName()));
                    }
                }
            }

            setArtistName(artist.getName());
            setListeners(artist.getListeners());
        }

        private void setPositionTextView(int position) {
            positionTextView.setText(String.valueOf(position));
        }

        private void setImage(String imageUrl) {
            Picasso.get().load(imageUrl).into(imageView);
        }

        private void setArtistName(String artistName) {
            artistNameTextView.setText(artistName);
        }

        private void setListeners(String listeners) {
            listenersTextView.setText("Listeners: " + listeners);
        }
    }
}
