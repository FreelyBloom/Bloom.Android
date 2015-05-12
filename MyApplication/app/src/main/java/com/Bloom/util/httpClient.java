package com.Bloom.util;

/**
 * Created by Im on 5/11/15.
 */

import com.loopj.android.http.*;
public class httpClient {
    //http://192.168.219.105:52273/
    private static final String BASE_URL = "http://maden.kr:52271"; // 128.199.247.184;
    private static RequestParams paramList;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static AsyncHttpClient getInstance() {
        client.setURLEncodingEnabled(false);
        return httpClient.client;
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void get(String url, RequestParams jtoString, AsyncHttpResponseHandler responseHandler) {
        paramList = new RequestParams("JSONData", jtoString);
        client.get(url, paramList, responseHandler);
    }
}