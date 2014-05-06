package com.artemzin.android.sample.m123fly.ui.fragment;

import android.os.Bundle;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class FlightsListFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // for correct handling screen rotations
    }


}
