package com.spy.spycamera.presentation.ui.fragments;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spy.spycamera.presentation.util.SoftKeyboardUtil;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user on 6/14/16.
 */
public abstract class BaseFragment extends Fragment {

    private View parentView;

    /**
     * Get resource layout from child
     *
     * @return R.layout.x (integer)
     */
    public abstract int getLayoutResource();

    /**
     * This is case run onCreate
     *
     * @param savedInstanceState
     */
    public abstract void initViews(Bundle savedInstanceState);

    // Butter-knife
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutResource(), container, false);
        unbinder = ButterKnife.bind(this, parentView);
        initViews(savedInstanceState);
        return parentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    @Override
    public void onResume() {
        super.onResume();
        SoftKeyboardUtil.hideKeyboard(getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        SoftKeyboardUtil.hideKeyboard(getActivity());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != unbinder) {
            unbinder.unbind();
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String getTagName() {
        try {
            Field field = getClass().getField("TAG_NAME");
            return (String) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

}
