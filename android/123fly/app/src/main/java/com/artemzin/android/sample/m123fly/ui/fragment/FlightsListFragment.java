package com.artemzin.android.sample.m123fly.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.artemzin.android.sample.m123fly.R;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class FlightsListFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // for correct handling screen rotations
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flights_list, container, false);
    }
}
