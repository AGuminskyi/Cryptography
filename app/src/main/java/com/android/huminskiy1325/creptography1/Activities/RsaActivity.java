package com.android.huminskiy1325.creptography1.Activities;

import android.support.v4.app.Fragment;

import com.android.huminskiy1325.creptography1.Fragments.RsaFragment;

/**
 * Created by cubru on 27.05.2017.
 */

public class RsaActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RsaFragment();
    }
}
