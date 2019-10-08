package com.example.lastfm.presentation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.lastfm.R;
import com.squareup.picasso.Picasso;

public class InfoDetailFragment extends Fragment {

    private static final String IMAGE_URL = "image_url";
    private static final String DETAIL_NAME = "detail_name";

    public static InfoDetailFragment newInstance(String url, String name) {
        Bundle bundle = new Bundle();

        bundle.putString(IMAGE_URL, url);
        bundle.putString(DETAIL_NAME, name);

        InfoDetailFragment infoDetailFragment = new InfoDetailFragment();

        infoDetailFragment.setArguments(bundle);
        return infoDetailFragment;
    }

    public InfoDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_detail, container, false);

        Picasso.get().load(getArguments().getString(IMAGE_URL)).into((ImageView) view.findViewById(R.id.image_item));
        ((TextView) view.findViewById(R.id.detail_name_item)).setText(getArguments().getString(DETAIL_NAME));
        return view;
    }
}
