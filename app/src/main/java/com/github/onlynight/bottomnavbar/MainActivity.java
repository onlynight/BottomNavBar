package com.github.onlynight.bottomnavbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.onlynight.bottomnavbar.library.BottomNavBar;

public class MainActivity extends AppCompatActivity implements BottomNavBar.OnItemSelectedListener {

    private NavItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavBar navBar = findViewById(R.id.navBar);

        mAdapter = new NavItemAdapter();
        navBar.setAdapter(mAdapter);
        navBar.setOnItemSelectedListener(this);
        navBar.setSelect(0);
    }

    @Override
    public void onSelected(int position) {
        Toast.makeText(this, "position = " + position, Toast.LENGTH_SHORT).show();
    }
}
