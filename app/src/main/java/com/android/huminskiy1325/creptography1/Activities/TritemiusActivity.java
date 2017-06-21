package com.android.huminskiy1325.creptography1.Activities;

import android.support.v4.app.Fragment;

import com.android.huminskiy1325.creptography1.Fragments.TritemiusFragment;

/**
 * Created by cubru on 18.03.2017.
 */

public class TritemiusActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TritemiusFragment();
    }

    @Override
    protected boolean getActionBarFromActivity() {
        return true;
    }
}
