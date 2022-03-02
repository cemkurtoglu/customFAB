package com.cemkurtoglu.fabmenu;

import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;


public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        String currentFragment = getIntent().getExtras().getString("current fragment"); // To identify the fragment that we are at
        initView();
        initTransitionSettings();
    }

    private void initView(){
        AppCompatEditText searchView = findViewById(R.id.searchBar);
        searchView.setTextColor(getResources().getColor(R.color.white));
        searchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        });


    }

    private void initTransitionSettings(){
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container),true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground,true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
