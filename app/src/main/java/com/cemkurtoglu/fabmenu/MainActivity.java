package com.cemkurtoglu.fabmenu;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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


    @Override
    public void onFabItemClick(View button) {

        Fragment selectedFragment = null;

        switch (button.getId()){
            case R.id.searchButton:
                Log.e("Needs tailoring","Error");
//                selectedFragment = new SearchActivity();
                break;
            case R.id.homeButton:
                selectedFragment = new HomeFragment();
                break;
            case R.id.statisticsButton:
                selectedFragment = new GraphFragment();
                break;
            case R.id.profileButton:
                selectedFragment = new ProfileFragment();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,selectedFragment).commit();

    }
}