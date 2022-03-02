package com.cemkurtoglu.fabmenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements CustomFabMenu{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFabMenu();
    }


    private void initFabMenu(){
        FabMenu fabMenu = new FabMenu(this,this);
        addContentView(fabMenu.getRootView(), fabMenu.getLayoutParams());
        fabMenu.setSelectedItemId(R.id.homeButton);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onFabItemClick(View button) {

        Fragment selectedFragment = null;
        Activity selectedActivity = null;
        int select = 0;

        switch (button.getId()){
            case R.id.searchButton:
                selectedActivity = new SearchActivity();
                select =1;
                break;
            case R.id.homeButton:
                selectedFragment = new HomeFragment();
                select =0;
                break;
            case R.id.statisticsButton:
                selectedFragment = new GraphFragment();
                select =0;
                break;
            case R.id.profileButton:
                selectedFragment = new ProfileFragment();
                select =0;
                break;
        }

//                Intent intent = new Intent(activity, SearchFragment.class);
//                intent.putExtra("current fragment",fragmentTag);
//                ActivityOptionsCompat optionsCompat
//                        = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, searchButton, "shared_element_searchbar");
//                activity.startActivity(intent, optionsCompat.toBundle());
        if (select == 0){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,selectedFragment).commit();
        } else {
            Intent intent = new Intent(this, SearchActivity.class);
//            intent.putExtra("current fragment",fragmentTag);
            ActivityOptionsCompat optionsCompat
                    = ActivityOptionsCompat.makeSceneTransitionAnimation(this, button, "shared_element_searchbar");
            startActivity(intent, optionsCompat.toBundle());
        }


    }
}