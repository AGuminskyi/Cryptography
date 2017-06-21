package com.android.huminskiy1325.creptography1.Activities;

import android.support.v4.app.Fragment;

import com.android.huminskiy1325.creptography1.Fragments.StirlFragment;

/**
 * Created by cubru on 18.05.2017.
 */

public class StirlActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new StirlFragment();
    }

    @Override
    protected boolean getActionBarFromActivity() {
        return true;
    }
}
