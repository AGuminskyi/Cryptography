package com.android.huminskiy1325.creptography1.Activities;

import android.support.v4.app.Fragment;

import com.android.huminskiy1325.creptography1.Fragments.GammaFragment;

/**
 * Created by cubru on 26.04.2017.
 */

public class GammaActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new GammaFragment();
    }

    @Override
    protected boolean getActionBarFromActivity() {
        return true;
    }
}
