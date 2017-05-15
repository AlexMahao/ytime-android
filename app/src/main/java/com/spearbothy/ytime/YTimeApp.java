package com.spearbothy.ytime;

import android.app.Application;

import com.spearbothy.ytime.adapter.OkHttpAdapter;
import com.spearbothy.ytime.net.HttpUtils;
import com.spearbothy.ytime.net.adapter.GsonParserAdapter;

/**
 * Created by mahao on 17-5-9.
 */

public class YTimeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpUtils.setsIParser(new GsonParserAdapter());
        HttpUtils.setHttpClientAdapter(new OkHttpAdapter());
    }
}
