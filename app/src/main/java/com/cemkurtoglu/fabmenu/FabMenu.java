package com.cemkurtoglu.fabmenu;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabMenu {

    private final View rootView;
    private final Resources resources;
    private LinearLayout layoutSettings, layoutDelete, layoutAdd, layoutSearch, layoutHome, layoutProfile, layoutStatistics;
    private FloatingActionButton mainButton, settingsButton, deleteButton, addButton, homeButton, profileButton, statisticsButton;
    private View fabBlackOut;
    private MaterialButton searchButton;
    private boolean isMenuClosed;
    private int searchButtonWidth;
    private String fragmentTag = null;


    /**
     * @author Cem
     * This is a custom written FabMenu class that is designed to implement on an Activity that extends it's {@link FabMenuAppCompatActivity}
     * FabMenuAppCompatActivity contains three fragment objects that needs to be changed for different uses. //TO DO//
     * @param context
     */


    public FabMenu(FabMenuAppCompatActivity context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.fab_layout,null,false);
        resources = context.getResources();
        initView();
        initFab();
        initOnFabBlackOutClick();
        initOnSearchClick(context);
        initFragmentButtons(context,homeButton);
        initFragmentButtons(context,statisticsButton);
        initFragmentButtons(context,profileButton);
    }

    public View getRootView(){
        return rootView;
    }


    public CoordinatorLayout.LayoutParams getLayoutParams(){
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.anchorGravity = Gravity.BOTTOM | Gravity.END;
        return layoutParams;
    }


    private void initView(){
        //init layouts
        layoutSettings = findViewById(R.id.layout_settingsButton);
        layoutDelete = findViewById(R.id.layout_deleteButton);
        layoutAdd = findViewById(R.id.layout_addButton);
        layoutSearch = findViewById(R.id.layout_search);
        fabBlackOut = findViewById(R.id.fab_blackout_layout);
        layoutHome = findViewById(R.id.layout_home);
        layoutProfile = findViewById(R.id.layout_profile);
        layoutStatistics = findViewById(R.id.layout_statistics);
        //init buttons
        settingsButton = findViewById(R.id.settingsButton);
        deleteButton = findViewById(R.id.deleteButton);
        addButton = findViewById(R.id.addButton);
        searchButton = findViewById(R.id.searchButton);
        searchButtonWidth = searchButton.getLayoutParams().width;
        homeButton = findViewById(R.id.homeButton);
        profileButton = findViewById(R.id.profileButton);
        statisticsButton = findViewById(R.id.statisticsButton);
        //init main FAB button
        mainButton = findViewById(R.id.mainMenuButton);
        isMenuClosed = true;
    }

    private  <T extends View> T findViewById(@IdRes int id) {
        return getRootView().findViewById(id);
    }

    private void initFab(){

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuClosed) {
                    menuOpen();
                } else {
                    menuClose();
                }
            }
        });


        mainButton.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                mainButton.setClickable(false);
                fabBlackOut.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mainButton.setClickable(true);
                fabBlackOut.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        initLastLayoutListener();

    }


    private void menuClose() {

        mainButton.animate().rotationBy(-90);
        animateMenu();
        this.isMenuClosed = true;
    }

    private void menuOpen(){
        mainButton.animate().rotationBy(90);
        animateMenu();
        this.isMenuClosed = false;
    }

    private void animateMenu() {

        if(isMenuClosed){
            setLayoutVisibility(View.VISIBLE);
            animateVerticalMenu(
                    -resources.getDimension(R.dimen.standard_55),
                    -resources.getDimension(R.dimen.standard_100),
                    -resources.getDimension(R.dimen.standard_145));
            animateHorizontalMenu(
                    -resources.getDimension(R.dimen.standard_55),
                    -resources.getDimension(R.dimen.standard_100),
                    -resources.getDimension(R.dimen.standard_145),
                    -resources.getDimension(R.dimen.standard_190));
            animateShapeExpand(searchButton,searchButtonWidth);
        } else {
            animateVerticalMenu(0, 0, 0);
            animateHorizontalMenu(0,0,0,0);
            animateShapeShrink(searchButton);
        }

    }

    private void animateHorizontalMenu(float v, float v1, float v2, float v3) {
        layoutProfile.animate().translationX(v);
        layoutStatistics.animate().translationX(v1);
        layoutHome.animate().translationX(v2);
        layoutSearch.animate().translationX(v3);
    }

    private void animateVerticalMenu(float v, float v2, float v3) {
        layoutSettings.animate().translationY(v);
        layoutDelete.animate().translationY(v2);
        layoutAdd.animate().translationY(v3);
    }

    /**
     * @author Cem
     * Last layout in the vertical menu option needs to be initialized manually to set the visibility to 0.
     * In this case the top most button in the vertical pop-up menu is "ADD" button.
     *
     */
    private void initLastLayoutListener(){
        layoutAdd.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(isMenuClosed)setLayoutVisibility(View.GONE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void setLayoutVisibility(int viewVisibility) {

        layoutSettings.setVisibility(viewVisibility);
        layoutDelete.setVisibility(viewVisibility);
        layoutAdd.setVisibility(viewVisibility);
        layoutSearch.setVisibility(viewVisibility);
        fabBlackOut.setVisibility(viewVisibility);
        layoutHome.setVisibility(viewVisibility);
        layoutStatistics.setVisibility(viewVisibility);
        layoutProfile.setVisibility(viewVisibility);
    }

    private void animateShapeShrink(final View view) {

        ValueAnimator anim = ValueAnimator.ofInt(view.getLayoutParams().width,0);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = val;
                view.requestLayout();
            }
        });

        anim.setDuration(400);
        anim.start();
    }

    /**
     * Animate view to get expand effect.
     *
     * @param view
     */
    private void animateShapeExpand(final View view, int initialLayoutWidth) {

        ValueAnimator anim = ValueAnimator.ofInt(55,initialLayoutWidth);

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = val;
                view.requestLayout();
            }
        });

        anim.setDuration(400);
        anim.start();
    }

    private void initOnSearchClick(FabMenuAppCompatActivity activity){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, SearchActivity.class);
                intent.putExtra("current fragment",fragmentTag);
                ActivityOptionsCompat optionsCompat
                        = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, searchButton, "shared_element_searchbar");
                activity.startActivity(intent, optionsCompat.toBundle());
            }
        });

    }


    private void initOnFabBlackOutClick() {
        fabBlackOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuClose();
            }

        });
    }



    private void initFragmentButtons(FabMenuAppCompatActivity activity,FloatingActionButton button){

        Fragment currentFragment = null;
        if (button == profileButton){
            currentFragment = activity.getProfileFragment();
            fragmentTag = "Profile Fragment";
        } else if (button == statisticsButton){
            currentFragment = activity.getGraphFragment();
            fragmentTag = "Statistics Fragment";
        } else if (button == homeButton){
            currentFragment = activity.getHomeFragment();
            fragmentTag = "Home Fragment";
        } else{
            Log.e("initFragment Error","Wrong button input");
        }

        Fragment finalCurrentFragment = currentFragment;
        String finalTag = fragmentTag;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_fragment, finalCurrentFragment, finalTag)
                        .commit();

                fragmentTag = finalTag;

            }
        });
    }

}

class FabMenuAppCompatActivity extends AppCompatActivity {

    private final HomeFragment homeFragment = new HomeFragment();
    private final ProfileFragment profileFragment = new ProfileFragment();
    private final GraphFragment graphFragment = new GraphFragment();

    ProfileFragment getProfileFragment() {
        return profileFragment;
    }

    HomeFragment getHomeFragment(){
        return homeFragment;
    }

    GraphFragment getGraphFragment() {
        return graphFragment;
    }



}
