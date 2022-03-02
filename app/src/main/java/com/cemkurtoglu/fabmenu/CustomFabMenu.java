package com.cemkurtoglu.fabmenu;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public interface CustomFabMenu {

//    void setContentView(@NonNull View FabMenuRootView, @NonNull CoordinatorLayout.LayoutParams FabMenuLayoutParams);

    void onFabItemClick(View button);

    interface OnItemSelectedListener {
        boolean onFabItemSelected(@NonNull View button);
    }

}
