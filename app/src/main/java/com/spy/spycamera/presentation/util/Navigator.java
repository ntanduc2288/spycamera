package com.spy.spycamera.presentation.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

/**
 * @author Duc Nguyen
 * @version 1.0
 * @since 11/4/16
 */
public class Navigator {
    /**
     * Replace fragment
     *
     *
     * @param containerId     is view declare in xml, but not fragment view
     * @param fragment
     * @param fragmentManager
     * @param isAddBackStack
     */
    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int containerId, boolean isAddBackStack) {
        if ((0 == containerId) || (null == fragment)) {
            return;
        }
        // Auto get tag by fragment full path
        String tag = getTagByFragment(fragment);

        FragmentTransaction ft = createTransaction(fragmentManager);

        if (TextUtils.isEmpty(tag)) {
            // This case is very infrequent, because contentId is view but not fragment view (declare in xml)
            ft.replace(containerId, fragment);
        } else {
            ft.replace(containerId, fragment, tag);
        }

        commitTransaction(ft, fragment, isAddBackStack);
    }

    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int containerId, String tag, boolean isAddBackStack) {
        if ((0 == containerId) || (null == fragment)) {
            return;
        }

        FragmentTransaction ft = createTransaction(fragmentManager);

        ft.replace(containerId, fragment, tag);
        commitTransaction(ft, fragment, isAddBackStack);
    }

    /**
     * Begin transaction with fragment manager
     *
     * @return
     */
    private FragmentTransaction createTransaction(FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        //ft.setCustomAnimations(R.anim.slide_right_to_left, 0, 0, R.anim.slide_left_to_right);
        return ft;
    }

    /**
     * Commit transaction with back stack
     *
     * @param ft
     * @param fr
     * @param isAddBackStack
     */
    private void commitTransaction(FragmentTransaction ft, Fragment fr, boolean isAddBackStack) {
        if (isAddBackStack) {
            ft.addToBackStack(getTagByFragment(fr));
        }
//        // Cheat crash add, replace fragment when run in background
//        if (PijonApplication.getInstance().isPause) {
//            ft.commitAllowingStateLoss();
//        } else {
//            ft.commit();
//        }
        ft.commit();
    }

    /**
     * Get tag by fragment
     *
     * @param fr
     * @return
     */
    private String getTagByFragment(Fragment fr) {
        if (null != fr) {
            return fr.getClass().getName();
        }
        return null;
    }
}
