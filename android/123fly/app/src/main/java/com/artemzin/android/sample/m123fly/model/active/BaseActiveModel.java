package com.artemzin.android.sample.m123fly.model.active;

import android.os.Handler;
import android.os.Looper;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public abstract class BaseActiveModel {

    /**
     * Callback interface for model listeners
     *
     * NOTICE: every method will be called in UI thread
     * @param <T> class of the data which will be returned to listener as result
     */
    public interface Listener<T> {
        /**
         * Called in UI thread right before processing starts
         */
        void onPreExecute();

        /**
         * Called in UI thread if exception occurred while processing data
         * @param e exception
         */
        void onExceptionOccurred(Exception e);

        /**
         * Called in UI thread when data processed successfully
         * @param data result of work
         */
        void onDataProcessed(T data);
    }

    private static final Handler mUIThreadHandler = new Handler(Looper.getMainLooper());

    protected Handler getUIThreadHandler() {
        return mUIThreadHandler;
    }
}
