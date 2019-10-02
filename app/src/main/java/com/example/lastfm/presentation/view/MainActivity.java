package com.example.lastfm.presentation.view;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import com.example.lastfm.R;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
    }
}
