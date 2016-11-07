package com.spy.spycamera.presentation.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.spy.spycamera.R;
import com.spy.spycamera.presentation.MyApp;
import com.spy.spycamera.presentation.ui.fragments.ForegroundCameraFragment;
import com.spy.spycamera.presentation.util.Navigator;
import com.spy.spycamera.presentation.util.UserManager;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    Navigator navigator;

    @Inject
    UserManager userManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApp)getApplication()).getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        navigator.replaceFragment(getSupportFragmentManager(), new ForegroundCameraFragment(), R.id.frContainer, true);

    }
}
