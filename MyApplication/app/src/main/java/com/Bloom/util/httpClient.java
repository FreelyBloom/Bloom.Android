package com.Bloom.util;

/**
 * Created by Im on 5/11/15.
 */

import android.content.Context;

import com.loopj.android.http.*;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;

public class httpClient {
    //http://192.168.219.105:52273/
    private static final String BASE_URL = "http://maden.kr:52271";// 128.199.247.184;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static AsyncHttpClient getInstance() {

        return httpClient.client;
    }

    public static void post(String url,RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url),params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

}