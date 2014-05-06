package com.artemzin.android.sample.m123fly.api;

import com.artemzin.android.sample.m123fly.api.json_parsing.FlightsResponseJSONParser;
import com.artemzin.android.sample.m123fly.api.response.FlightsResponse;
import com.artemzin.android.sample.m123fly.network.NetworkException;
import com.artemzin.android.sample.m123fly.network.NetworkRequest;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class Fly123Api {
    private static volatile Fly123Api sInstance;

    public static Fly123Api getInstance() {
        if (sInstance == null) {
            synchronized (Fly123Api.class) {
                if (sInstance == null) {
                    sInstance = new Fly123Api();
                }
            }
        }

        return sInstance;
    }

    public FlightsResponse getFlights() throws Fly123ApiException, NetworkException {
        try {
            return FlightsResponseJSONParser
                    .parseFlightsResponse(NetworkRequest
                            .getRequest("http://test.morbo.ru/test.json")
                            .getResponse()
            );
        } catch (Exception e) {
            throw new Fly123ApiException(e);
        }
    }
}
