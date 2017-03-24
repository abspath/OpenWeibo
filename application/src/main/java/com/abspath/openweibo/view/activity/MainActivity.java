package com.abspath.openweibo.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.abspath.openweibo.view.fragment.MainFragment;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Fragment getFragment() {
        return new MainFragment();
    }

}

