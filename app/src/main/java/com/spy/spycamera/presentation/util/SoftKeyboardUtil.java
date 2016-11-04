package com.spy.spycamera.presentation.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Nguyen on 8/16/2016.
 * <p>
 * This utility contains functions to work with soft keyboard, ex: hide keyboard immediately.
 */
public class SoftKeyboardUtil {

    public static void toggleKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    /**
     * Hide the keyboard immediately.
     */
    public static void hideKeyboard(Context context, IBinder binder) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binder, 0);
    }

    /**
     * Hide the keyboard immediately.
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view != null) {
            SoftKeyboardUtil.hideKeyboard(activity, view.getWindowToken());
        }
    }

    public static void showKeyboard(Context context, IBinder binder) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(binder, 0);
    }

    public static void showKeyboard(Activity context) {
        View view = context.getCurrentFocus();
        if (view != null) {
            SoftKeyboardUtil.showKeyboard(context, view.getWindowToken());
        }
    }
}
