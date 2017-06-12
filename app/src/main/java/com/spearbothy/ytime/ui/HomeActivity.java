package com.spearbothy.ytime.ui;

import com.spearbothy.ytime.BaseActivity;
import com.spearbothy.ytime.dagger.ActivityComponent;
import com.spearbothy.ytime.presenter.HomePresenter;
import com.spearbothy.ytime.view.HomeView;

/**
 * Created by #author on 2017/06/12 18:23.
 */

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeView {
    @Override
    protected void afterCreate() {

    }

    @Override
    protected void inject(ActivityComponent dagger) {
        dagger.inject(this);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
