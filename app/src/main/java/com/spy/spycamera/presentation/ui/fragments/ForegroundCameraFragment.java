package com.spy.spycamera.presentation.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.spy.spycamera.R;
import com.spy.spycamera.presentation.MyApp;
import com.spy.spycamera.presentation.util.Navigator;

import javax.inject.Inject;

/**
 * @author Duc Nguyen
 * @version 1.0
 * @since 11/4/16
 */
public class ForegroundCameraFragment extends BaseFragment{

    @Inject
    Navigator navigator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp)getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.camera_view;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }
}
