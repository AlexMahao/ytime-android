package com.spearbothy.ytime;

import android.app.Application;

import com.spearbothy.ytime.netimpl.NetImpl;

/**
 * Created by mahao on 17-5-9.
 */

public class YTimeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetImpl.init();
    }
}
