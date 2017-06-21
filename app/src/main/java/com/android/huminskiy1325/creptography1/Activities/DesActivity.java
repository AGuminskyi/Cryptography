package com.android.huminskiy1325.creptography1.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.android.huminskiy1325.creptography1.Fragments.DesFragment;
import com.android.huminskiy1325.creptography1.R;

/**
 * Created by cubru on 21.05.2017.
 */

public class DesActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new DesFragment();
    }

    @Override
    protected boolean getActionBarFromActivity() {
        return true;
    }

}
