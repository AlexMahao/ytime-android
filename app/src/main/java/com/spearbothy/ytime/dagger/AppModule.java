package com.spearbothy.ytime.dagger;


import com.spearbothy.ytime.YTimeApp;
import com.spearbothy.ytime.utils.SPUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex_mahao on 2016/8/3.
 */
@Module
public class AppModule {

    private YTimeApp app;

    public AppModule(YTimeApp app) {
        this.app = app;
    }

    @Singleton
    @Provides
    YTimeApp provideContext() {
        return app;
    }

    @Singleton
    @Provides
    SPUtils provideSPUtils(YTimeApp app){
        return new SPUtils(app);
    }
}
