package com.cemkurtoglu.fabmenu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends FabMenuAppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFabMenu();
        initMainView();
    }

    private void initMainView() {
        SomeFragment someFragment = new SomeFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_fragment, someFragment,"Some Fragment")
                .commit();
    }

    private void initFabMenu(){
        FabMenu fabMenu = new FabMenu(this);
        addContentView(fabMenu.getRootView(), fabMenu.getLayoutParams());
    }


}