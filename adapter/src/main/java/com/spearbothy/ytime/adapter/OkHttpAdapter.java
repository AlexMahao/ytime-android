package com.spearbothy.ytime.adapter;

import android.os.Handler;
import android.os.Looper;

import com.spearbothy.ytime.net.HttpRequest;
import com.spearbothy.ytime.net.HttpResult;
import com.spearbothy.ytime.net.INetAdapter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by mahao on 17-5-9.
 */

public class OkHttpAdapter implements INetAdapter {

    private static final HttpLoggingInterceptor.Level LOG_LEVEL = HttpLoggingInterceptor.Level.BODY;

    private OkHttpClient mClient;

    private Handler handler = new Handler(Looper.getMainLooper());

    public OkHttpAdapter() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(LOG_LEVEL);
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = client.newBuilder()
                .protocols(Util.immutableList(Protocol.HTTP_1_1, Protocol.HTTP_2))//just for http1.1
                .connectTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .writeTimeout(30L, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(logging);

        }
        mClient = builder.build();
    }

    @Override
    public void execute(HttpRequest request, final HttpResult<String> httpResult) {
        Request.Builder builder = new Request.Builder()
                .cacheControl(CacheControl.FORCE_NETWORK)
                .tag(request.getTag())
                .headers(createHeaders(request.getHeaderParams()));
        if (request.getMethod() == com.spearbothy.ytime.net.HttpMethod.POST) {
            builder.url(request.getUrl());
            builder.method("POST", createBody(request.getParams()));
        } else if (request.getMethod() == com.spearbothy.ytime.net.HttpMethod.GET) {
            builder.method("GET", null);
            builder.url(request.getUrl() + buildGetParams(request.getParams()));
        }

        Call call = mClient.newCall(builder.build());

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpResult.onError(HttpResult.NET_ERROR, e.getMessage());
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            httpResult.onSuccess(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                            httpResult.onError(HttpResult.NET_ERROR, e.getMessage());
                        }
                    }
                });
            }
        });
    }

    @Override
    public void cancel(String tag) {

    }

    private String buildGetParams(Map<String, String> params) {
        if (params != null && params.size() > 0) {
            Iterator<String> it = params.keySet().iterator();
            StringBuffer sb = null;
            while (it.hasNext()) {
                String key = it.next();
                String value = params.get(key);
                if (sb == null) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key);
                sb.append("=");
                sb.append(value);
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * 创建header
     */
    private Headers createHeaders(Map<String, String> params) {
        Headers.Builder builder = new Headers.Builder();
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        Iterator<Map.Entry<String, String>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    /**
     * 创建参数
     *
     * @param params
     * @return
     */
    private FormBody createBody(Map<String, String> params) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        Iterator<Map.Entry<String, String>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            formBodyBuilder.add(entry.getKey(), entry.getValue());
        }
        return formBodyBuilder.build();
    }
}
