package com.spearbothy.ytime;

import android.app.Application;

import com.spearbothy.ytime.dagger.AppComponent;
import com.spearbothy.ytime.dagger.AppModule;
import com.spearbothy.ytime.dagger.DaggerAppComponent;
import com.spearbothy.ytime.netimpl.NetImpl;

/**
 * Created by mahao on 17-5-9.
 */

public class YTimeApp extends Application {

    public static YTimeApp sApp;
    // dagger2 依赖
    public static AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        NetImpl.init();
        initDagger();
    }

    private void initDagger() {
        sAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
