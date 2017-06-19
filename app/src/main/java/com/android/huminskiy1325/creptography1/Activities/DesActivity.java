package com.android.huminskiy1325.creptography1.Activities;

import android.support.v4.app.Fragment;

import com.android.huminskiy1325.creptography1.Fragments.DesFragment;

/**
 * Created by cubru on 21.05.2017.
 */

public class DesActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DesFragment();
    }
}
