package com.example.lastfm.presentation.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.lastfm.presentation.topartists.view.TopArtistsFragment;
import com.example.lastfm.presentation.toptracks.view.TopTracksFragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TopTracksFragment();
        }
        return new TopArtistsFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
