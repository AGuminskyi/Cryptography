package com.android.huminskiy1325.creptography1.Activities;

import android.support.v4.app.Fragment;

import com.android.huminskiy1325.creptography1.Fragments.ChooseLabFragment;

/**
 * Created by cubru on 08.03.2017.
 */

public class ChooseLabActivity extends SingleFragmentActivity {


    @Override
    protected boolean getActionBarFromActivity() {
        return false;
    }

    @Override
    protected Fragment createFragment() {
        return new ChooseLabFragment();
    }

}
