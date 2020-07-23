package com.example.wastebuddy.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.wastebuddy.Navigation;
import com.example.wastebuddy.R;
import com.example.wastebuddy.databinding.ActivityMainBinding;
import com.example.wastebuddy.fragments.HomeFragment;
import com.example.wastebuddy.fragments.ScannerFragment;
import com.example.wastebuddy.fragments.SearchFragment;
import com.example.wastebuddy.models.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseQuery;
import com.paulrybitskyi.persistentsearchview.PersistentSearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    final FragmentManager fragmentManager = getSupportFragmentManager();

    SearchFragment mSearchFragment;

    ActivityMainBinding mBinding;
    BottomNavigationView mBottomNavigationView;
    PersistentSearchView searchView;

    List<Item> mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        searchView = mBinding.persistentSearchView;
        mResults = new ArrayList<>();
        configureSearchView();

        mBottomNavigationView = mBinding.bottomNavigationView;
        setBottomNavItemSelectedListener();
    }

    public void replaceFragment(Fragment fragment) {
        if (fragment instanceof HomeFragment || fragment instanceof SearchFragment) {
            searchView.setVisibility(View.VISIBLE);
        } else {
            searchView.setVisibility(View.GONE);
        }

        if (mBottomNavigationView.getVisibility() == View.GONE)
            mBottomNavigationView.setVisibility(View.VISIBLE);

        if (fragment instanceof ScannerFragment) mBottomNavigationView.setVisibility(View.GONE);

        fragmentManager.beginTransaction().replace(mBinding.containerFrameLayout.getId(),
                fragment).commit();
    }

    private void setBottomNavItemSelectedListener() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.homeMenuItem:
                    fragment = new HomeFragment();
                    break;
                case R.id.searchMenuItem:
                default:
                    fragment = new SearchFragment();
                    break;
            }
            replaceFragment(fragment);
            return true;
        });
        // Set default selection
        mBottomNavigationView.setSelectedItemId(R.id.homeMenuItem);
    }

    private void configureSearchView() {
        searchView.setVoiceInputButtonEnabled(false);
        searchView.setSuggestionsDisabled(true);
        searchView.showRightButton();


        searchView.setOnLeftBtnClickListener(view -> {
            // Handle the left button click
            Toast.makeText(MainActivity.this, "Left button click", Toast.LENGTH_SHORT).show();
        });

        searchView.setOnClearInputBtnClickListener(view -> {
            // Handle the clear input button click
            mSearchFragment.showRecentItems();
        });

        searchView.setOnRightBtnClickListener(view -> Navigation.switchFragment(MainActivity.this, new ScannerFragment()));

        searchView.setRightButtonDrawable(getDrawable(R.drawable.ic_barcode_scanner_icon));

        searchView.setOnSearchConfirmedListener((searchView, queryInput) -> {
            // Handle a search confirmation. This is the place where you'd
            // want to perform a search against your data provider.

            // 1. Perform query against Parse
            query(queryInput);
            // Display list of items in recycler view
            searchView.collapse();

            mSearchFragment = new SearchFragment(mResults);
            replaceFragment(mSearchFragment);
        });

        // Disabling the suggestions since they are unused in
        // the simple implementation
    }

    private void query(String input) {
        // Specify which class to query
        ParseQuery<Item> parseQuery = ParseQuery.getQuery(Item.class);
        parseQuery.include(Item.KEY_AUTHOR);
        parseQuery.whereContains(Item.KEY_NAME, input);
        parseQuery.findInBackground((items, e) -> {
            if (e != null) {
                Log.e(TAG, "Problem  with querying items", e);
                return;
            }
            for (Item item : items) {
                Log.i(TAG,
                        "Item: " + item.getName() + ", Name: " + item.getAuthor().getUsername());
            }
            mResults.clear();
            mSearchFragment.updateData(items);
        });
    }
}