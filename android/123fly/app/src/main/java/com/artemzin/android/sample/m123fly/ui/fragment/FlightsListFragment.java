package com.artemzin.android.sample.m123fly.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.artemzin.android.sample.m123fly.R;
import com.artemzin.android.sample.m123fly.api.response.FlightsResponse;
import com.artemzin.android.sample.m123fly.model.active.BaseActiveModel;
import com.artemzin.android.sample.m123fly.model.active.FlightsActiveModel;
import com.artemzin.android.sample.m123fly.ui.adapter.FlightsListAdapter;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class FlightsListFragment extends BaseFragment {

    private static final String TAG = "FlightsListFragment";

    private FlightsActiveModel mFlightsActiveModel;
    private View mLoadingUI, mLoadingErrorUI, mNoContentUI, mContentUI;
    private ListView mListView;

    private FlightsListAdapter mFlightsListAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (mFlightsListAdapter == null) {
            mFlightsListAdapter = new FlightsListAdapter(activity);
        } else {
            mFlightsListAdapter.setActivity(activity);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // for correct handling screen rotations

        mFlightsActiveModel = new FlightsActiveModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flights_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoadingUI      = view.findViewById(R.id.flights_list_loading_ui);
        mLoadingErrorUI = view.findViewById(R.id.flights_list_loading_error_ui);
        mNoContentUI    = view.findViewById(R.id.flights_list_no_content_ui);
        mContentUI      = view.findViewById(R.id.flights_list_content_ui);

        mListView = (ListView) mContentUI;

        mListView.setAdapter(mFlightsListAdapter);
    }

    @Override
    protected void setUIState(int state) {
        super.setUIState(state);

        mLoadingUI.setVisibility(state == UI_STATE_LOADING ? View.VISIBLE : View.GONE);
        mLoadingErrorUI.setVisibility(state == UI_STATE_LOADING_ERROR || state == UI_STATE_UNDEFINED ? View.VISIBLE : View.GONE);
        mNoContentUI.setVisibility(state == UI_STATE_EMPTY_OR_NO_CONTENT ? View.VISIBLE : View.GONE);
        mContentUI.setVisibility(state == UI_STATE_SHOW_CONTENT ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onFirstStart(Bundle savedInstanceState) {
        super.onFirstStart(savedInstanceState);
        mFlightsActiveModel.asyncGetFlights(new FlightsResponseListener());
    }

    private class FlightsResponseListener implements BaseActiveModel.Listener<FlightsResponse> {

        @Override
        public void onPreExecute() {
            try {
                setUIState(UI_STATE_LOADING);
            } catch (Exception e) {
                Log.e(TAG, "FlightsResponseListener.onPreExecute", e);
            }
        }

        @Override
        public void onExceptionOccurred(Exception e) {
            try {
                setUIState(UI_STATE_LOADING_ERROR);
            } catch (Exception e1) {
                Log.e(TAG, "FlightsResponseListener.onExceptionOccurred", e);
            }
        }

        @Override
        public void onDataProcessed(FlightsResponse data) {
            try {
                mFlightsListAdapter.setNewData(data.getDataList());
                setUIState(UI_STATE_SHOW_CONTENT);
            } catch (Exception e) {
                Log.e(TAG, "FlightsResponseListener.onDataProcessed", e);
            }
        }
    }
}
