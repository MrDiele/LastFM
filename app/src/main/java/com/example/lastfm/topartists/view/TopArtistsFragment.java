package com.example.lastfm.topartists.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.lastfm.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopArtistsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopArtistsFragment extends Fragment {

    public TopArtistsFragment() {
        // Required empty public constructor
    }

    public static TopArtistsFragment newInstance() {
        return new TopArtistsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

}
