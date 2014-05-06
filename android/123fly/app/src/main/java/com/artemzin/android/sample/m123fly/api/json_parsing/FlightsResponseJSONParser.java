package com.artemzin.android.sample.m123fly.api.json_parsing;

import com.artemzin.android.sample.m123fly.api.response.FlightsResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class FlightsResponseJSONParser {

    // If field is required, I use JSONObject.get... methods
    // otherwise, I use JSONObject.opt... methods

    public static FlightsResponse parseFlightsResponse(String jsonString) throws JSONException {
        FlightsResponse fr = new FlightsResponse();

        JSONObject jo = new JSONObject(jsonString);

        fr.setError(jo.getString("error"));

        JSONArray dataJArray = jo.optJSONArray("data");

        if (dataJArray == null || dataJArray.length() == 0) {
            fr.setDataList(new ArrayList<FlightsResponse.Data>(0));
        } else {
            final int count = dataJArray.length();
            List<FlightsResponse.Data> dataList = new ArrayList<FlightsResponse.Data>(count);

            for (int i = 0; i < count; i++) {
                dataList.add(parseData(dataJArray.getJSONObject(i)));
            }

            fr.setDataList(dataList);
        }

        return fr;
    }

    private static FlightsResponse.Data parseData(JSONObject dataJObject) throws JSONException {
        FlightsResponse.Data data = new FlightsResponse.Data();

        data.setHash(dataJObject.getString("hash"));
        data.setAirline(dataJObject.getString("airline"));
        data.setFlightTo(parseFlightTo(dataJObject.getJSONObject("flightTo")));

        JSONArray offersJArray = dataJObject.getJSONArray("offers");

        final int countOfOffers = offersJArray.length();
        List<FlightsResponse.Data.Offer> offerList = new ArrayList<FlightsResponse.Data.Offer>(countOfOffers);

        for (int i = 0; i < countOfOffers; i++) {
            offerList.add(parseOffer(offersJArray.getJSONObject(i)));
        }

        data.setOffers(offerList);

        return data;
    }

    private static FlightsResponse.Data.FlightTo parseFlightTo(JSONObject flightToJObject) throws JSONException {
        FlightsResponse.Data.FlightTo flightTo = new FlightsResponse.Data.FlightTo();

        flightTo.setDuration(flightToJObject.getLong("duration"));

        JSONArray segmentsJArray = flightToJObject.getJSONArray("segments");

        final int countOfSegments = segmentsJArray.length();
        List<FlightsResponse.Data.FlightTo.Segment> segmentsList = new ArrayList<FlightsResponse.Data.FlightTo.Segment>(countOfSegments);

        for (int i = 0; i < countOfSegments; i++) {
            segmentsList.add(parseSegment(segmentsJArray.getJSONObject(i)));
        }

        flightTo.setSegments(segmentsList);

        return flightTo;
    }

    private static FlightsResponse.Data.FlightTo.Segment parseSegment(JSONObject segmentJSONObject) throws JSONException {
        FlightsResponse.Data.FlightTo.Segment segment = new FlightsResponse.Data.FlightTo.Segment();

        segment.setAirline(segmentJSONObject.getString("airline"));
        segment.setFlightNumber(segmentJSONObject.getString("flightNumber"));
        segment.setPlane(segmentJSONObject.getString("plane"));
        segment.setDeparturePoint(segmentJSONObject.getString("depaturePoint")); // he he, correct variant "departure"
        segment.setArrivalPoint(segmentJSONObject.getString("arrivalPoint"));
        segment.setServiceClass(segmentJSONObject.getString("serviceClass"));
        segment.setDepartureTime(segmentJSONObject.getLong("depatureTime"));
        segment.setArrivalTime(segmentJSONObject.getLong("arrivalTime"));
        segment.setDuration(segmentJSONObject.getLong("duration"));
        segment.setLayoverTime(segmentJSONObject.getLong("layoverTime"));

        return segment;
    }

    private static FlightsResponse.Data.Offer parseOffer(JSONObject offerJSONObject) throws JSONException {
        FlightsResponse.Data.Offer offer = new FlightsResponse.Data.Offer();

        offer.setOrigin(offerJSONObject.getString("origin"));
        offer.setLink(offerJSONObject.getString("link"));
        offer.setPrice(new BigDecimal(offerJSONObject.getString("price")));

        return offer;
    }
}
