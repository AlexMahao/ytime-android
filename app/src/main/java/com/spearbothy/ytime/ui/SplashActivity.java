package com.spearbothy.ytime.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.widget.TextView;

import com.spearbothy.ytime.BaseActivity;
import com.spearbothy.ytime.R;
import com.spearbothy.ytime.dagger.ActivityComponent;
import com.spearbothy.ytime.presenter.SplashPresenter;
import com.spearbothy.ytime.view.SplashView;

import butterknife.BindView;

/**
 * Created by mahao on 17-6-7.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @BindView(R.id.title)
    TextView title;

    @Override
    protected void afterCreate() {
        title.animate().alpha(1)
                .setDuration(2000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        presenter.goHome();
                    }
                })
                .start();
    }

    @Override
    protected void inject(ActivityComponent dagger) {
        dagger.inject(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
