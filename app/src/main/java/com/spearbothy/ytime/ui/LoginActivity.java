package com.spearbothy.ytime.ui;

import android.support.v7.widget.CardView;

import com.spearbothy.ytime.BaseActivity;
import com.spearbothy.ytime.dagger.ActivityComponent;
import com.spearbothy.ytime.presenter.LoginPresenter;
import com.spearbothy.ytime.view.LoginView;
import com.spearbothy.ytime.R;

/**
 * Created by #author on 2017/06/12 18:22.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {
    @Override
    protected void afterCreate() {

    }

    @Override
    protected void inject(ActivityComponent dagger) {
        dagger.inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }
}
