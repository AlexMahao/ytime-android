package com.spearbothy.ytime.ui;

import com.spearbothy.ytime.BaseActivity;
import com.spearbothy.ytime.dagger.ActivityComponent;
import com.spearbothy.ytime.presenter.SplashPresenter;
import com.spearbothy.ytime.view.SplashView;

/**
 * Created by mahao on 17-6-7.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {
    @Override
    protected void afterCreate() {

    }

    @Override
    protected void inject(ActivityComponent dagger) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
