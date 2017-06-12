package com.spearbothy.ytime.dagger;


import com.spearbothy.ytime.ui.LoginActivity;
import com.spearbothy.ytime.ui.SplashActivity;

import dagger.Component;

/**
 * Created by alex_mahao on 2016/8/3.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);
}
