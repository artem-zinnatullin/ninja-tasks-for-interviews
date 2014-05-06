package com.artemzin.android.sample.m123fly.network;

/**
 * Wrapper for all exceptions which could occur in NetworkRequest
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class NetworkException extends Exception {

    public NetworkException(Throwable realCause) {
        super(realCause);
    }
}
