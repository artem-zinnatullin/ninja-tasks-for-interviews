package com.artemzin.android.sample.m123fly.model.active;

import com.artemzin.android.sample.m123fly.api.Fly123Api;
import com.artemzin.android.sample.m123fly.api.response.FlightsResponse;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class FlightsActiveModel extends BaseActiveModel {

    private Executor mExecutor = Executors.newSingleThreadExecutor();

    /**
     * Asynchronously gets flights from the 123fly api
     * @param listener for callbacks
     */
    public void asyncGetFlights(final Listener<FlightsResponse> listener) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                getUIThreadHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onPreExecute();
                    }
                });

                try {
                    final FlightsResponse flightsResponse = Fly123Api.getInstance().getFlights();
                    getUIThreadHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onDataProcessed(flightsResponse);
                        }
                    });
                } catch (final Exception e) {
                    getUIThreadHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onExceptionOccurred(e);
                        }
                    });
                }
            }
        });
    }
}
