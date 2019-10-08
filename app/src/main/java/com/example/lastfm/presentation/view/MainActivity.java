package com.example.lastfm.presentation.view;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.lastfm.R;

public class MainActivity extends AppCompatActivity implements InfoClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.cont, MainFragment.newInstance())
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        return true;
    }

    @Override
    public void onClick(String url, String name) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.cont, InfoDetailFragment.newInstance(url, name))
                .addToBackStack(null)
                .commit();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
