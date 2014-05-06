package com.artemzin.android.sample.m123fly.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

/**
 * @author Artem Zinnatullin [artem.zinnatullin@gmail.com]
 */
public class NetworkUtil {

    private NetworkUtil() {}

    /**
     * Converting input stream content to string
     * @param is input stream to convert
     * @return String with stream content
     * @throws java.io.IOException if problems with reading input stream
     */
    public static String convertStreamToString(final InputStream is) throws IOException {
        final InputStreamReader r = new InputStreamReader(is);
        final StringWriter sw = new StringWriter();

        final char[] buffer = new char[1024];

        try {
            for (int n; (n = r.read(buffer)) != -1;)
                sw.write(buffer, 0, n);
        } finally{
            try {
                is.close();
            } catch (IOException e) {
                // log it
            }
        }

        return sw.toString();
    }
}
