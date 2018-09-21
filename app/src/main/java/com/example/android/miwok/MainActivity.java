/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements CategoryFragment.ClickHandler {
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        isTwoPane = findViewById(R.id.frame_layout) != null;

        if (isTwoPane) categoryClick(R.string.category_numbers);
    }

    @Override
    public void categoryClick(int categoryResId) {
        if (isTwoPane) {
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
                    .replace(R.id.frame_layout, fragment)
                    .commit();
        }
        else {
            // Create a new intent to open the {@link WordActivity}
            Intent intent = new Intent(this, WordActivity.class);
            // Tell {@link WordActivity} which category fragment should be open
            intent.putExtra("category", categoryResId);
            // Start the new activity
            startActivity(intent);
        }
    }
}
