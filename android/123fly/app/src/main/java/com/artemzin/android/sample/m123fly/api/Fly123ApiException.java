package com.artemzin.android.sample.m123fly.api;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class Fly123ApiException extends Exception {

    public Fly123ApiException() {
        super();
    }

    public Fly123ApiException(String detailMessage) {
        super(detailMessage);
    }

    public Fly123ApiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public Fly123ApiException(Throwable throwable) {
        super(throwable);
    }
}
