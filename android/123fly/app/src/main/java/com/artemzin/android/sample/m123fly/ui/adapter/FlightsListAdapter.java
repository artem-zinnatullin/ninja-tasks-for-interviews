package com.artemzin.android.sample.m123fly.ui.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.artemzin.android.sample.m123fly.R;
import com.artemzin.android.sample.m123fly.api.response.FlightsResponse;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class FlightsListAdapter extends BaseAdapter {

    private List<FlightsResponse.Data> mDataList = new ArrayList<FlightsResponse.Data>(0);
    private WeakReference<Activity> mActivityWR;
    private Calendar mCalendarInstanceForDates = Calendar.getInstance();
    private DateFormat mDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

    public FlightsListAdapter(Activity activity) {
        if (activity == null) throw new IllegalArgumentException("Activity can not be null");
        mActivityWR = new WeakReference<Activity>(activity);
    }

    /**
     * Call this method when adapter should use new activity
     * @param activity to use
     */
    public void setActivity(Activity activity) {
        mActivityWR = new WeakReference<Activity>(activity);
    }

    public void setNewData(List<FlightsResponse.Data> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public FlightsResponse.Data getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDataList.get(position).getHash().hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mActivityWR.get()).inflate(R.layout.layout_flights_list_item, parent, false);
            viewHolder  = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        drawView(position, viewHolder);

        return convertView;
    }

    private void drawView(int position, ViewHolder viewHolder) {
        FlightsResponse.Data data = mDataList.get(position);

        FlightsResponse.Data.FlightTo.Segment firstSegment = data.getFlightTo().getSegments().get(0);

        viewHolder.departurePoint.setText(mActivityWR.get().getString(R.string.flight_from) + " " + firstSegment.getDeparturePoint());
        viewHolder.departureTime.setText(formatTime(firstSegment.getDepartureTime()));

        FlightsResponse.Data.FlightTo.Segment lastSegment = data.getFlightTo().getSegments().get(data.getFlightTo().getSegments().size() - 1);

        viewHolder.arrivalPoint.setText(mActivityWR.get().getString(R.string.flight_to) + " " + lastSegment.getArrivalPoint());
        viewHolder.arrivalTime.setText(formatTime(lastSegment.getArrivalTime()));

        viewHolder.duration.setText(mActivityWR.get().getString(R.string.flight_duration) + " " + formatDuration(data.getFlightTo().getDuration()));
    }

    private String formatTime(Long time) {
        if (time == null) {
            return mActivityWR.get().getString(R.string.flight_date_null);
        }

        mCalendarInstanceForDates.setTimeInMillis(time * 1000);
        return mDateFormat.format(mCalendarInstanceForDates.getTime());
    }

    private String formatDuration(Long duration) {
        if (duration == null) {
            return mActivityWR.get().getString(R.string.flight_duration_null);
        }

        return String.format("%dh %02dm", duration / 3600, (duration % 3600) / 60);
    }

    private static class ViewHolder {
        public final TextView departurePoint;
        public final TextView departureTime;
        public final TextView arrivalPoint;
        public final TextView arrivalTime;
        public final TextView duration;

        public ViewHolder(View view) {
            departurePoint = (TextView) view.findViewById(R.id.flights_list_item_departure_point_text_view);
            departureTime  = (TextView) view.findViewById(R.id.flights_list_item_departure_time_text_view);

            arrivalPoint = (TextView) view.findViewById(R.id.flights_list_item_arrival_point_text_view);
            arrivalTime  = (TextView) view.findViewById(R.id.flights_list_item_arrival_time_text_view);

            duration = (TextView) view.findViewById(R.id.flights_list_item_duration_text_view);
        }
    }
}
