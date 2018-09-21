package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        int categoryResId = getIntent().getIntExtra("category", 0);
        getSupportActionBar().setTitle(categoryResId);

        Fragment fragment;
        switch (categoryResId) {
            case R.string.category_numbers: fragment = new NumbersFragment(); break;
            case R.string.category_family: fragment = new FamilyFragment(); break;
            case R.string.category_colors: fragment = new ColorsFragment(); break;
            case R.string.category_phrases: fragment = new PhrasesFragment(); break;
            default: throw new IllegalArgumentException();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.frame_layout, fragment)
                .commit();
    }
}
