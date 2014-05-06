package com.artemzin.android.sample.m123fly.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public abstract class BaseFragment extends Fragment {

    protected static final int UI_STATE_UNDEFINED        = -1;
    protected static final int UI_STATE_LOADING          = 0;
    protected static final int UI_STATE_LOADING_ERROR    = 1;
    protected static final int UI_STATE_EMPTY_OR_NO_CONTENT = 2;
    protected static final int UI_STATE_SHOW_CONTENT     = 3;

    private static final String STATE_KEY_CURRENT_UI_STATE = "STATE_KEY_CURRENT_UI_STATE";

    private boolean mWasStarted;
    private Bundle mSavedInstanceState;
    private int mCurrentUIState = UI_STATE_UNDEFINED;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;

        if (savedInstanceState != null) {
            mCurrentUIState = savedInstanceState.getInt(STATE_KEY_CURRENT_UI_STATE, UI_STATE_UNDEFINED);
        }
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        setUIState(mCurrentUIState);
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!mWasStarted) {
            mWasStarted = true;
            onFirstStart(mSavedInstanceState);
            mSavedInstanceState = null;
        }
    }

    /**
     * Called once per fragment instance lifecycle. Called after onStart().
     * @param savedInstanceState state of previous fragment instance.
     */
    protected void onFirstStart(Bundle savedInstanceState) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_KEY_CURRENT_UI_STATE, mCurrentUIState);
        super.onSaveInstanceState(outState);
    }

    /**
     * Do not forget to call super.setUIState(state)
     * @param state to set
     */
    protected void setUIState(int state) {
        mCurrentUIState = state;
    }

    protected final int getUIState() {
        return mCurrentUIState;
    }
}
