package com.spearbothy.ytime.presenter;

import com.spearbothy.ytime.ui.LoginActivity;
import com.spearbothy.ytime.view.SplashView;

import javax.inject.Inject;

/**
 * Created by mahao on 17-6-7.
 */

public class SplashPresenter extends BasePresenter<SplashView> {

    @Inject
    public SplashPresenter() {

    }

    /**
     * 跳转首页
     */
    public void goHome() {
        view.intent2Activity(LoginActivity.class);
    }
}
