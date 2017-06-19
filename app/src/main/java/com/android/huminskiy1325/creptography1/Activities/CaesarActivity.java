package com.android.huminskiy1325.creptography1.Activities;

import android.support.v4.app.Fragment;

import com.android.huminskiy1325.creptography1.Fragments.CaesarFragment;

/**
 * Created by cubru on 08.03.2017.
 */

public class CaesarActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CaesarFragment();
    }
}
