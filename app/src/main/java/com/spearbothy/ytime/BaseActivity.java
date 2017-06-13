package com.spearbothy.ytime;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.spearbothy.ytime.dagger.ActivityComponent;
import com.spearbothy.ytime.dagger.ActivityModule;
import com.spearbothy.ytime.dagger.DaggerActivityComponent;
import com.spearbothy.ytime.presenter.IPresenter;
import com.spearbothy.ytime.utils.AppManager;
import com.spearbothy.ytime.view.IView;
import com.spearbothy.ytime.widget.LoadingDialog;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.Lazy;

/**
 * Created by alex_mahao on 2016/8/3.
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements IView {

    private String TAG = getClass().getSimpleName();

    @Inject
    public T presenter; // presenter对象

    @Inject
    protected Lazy<LoadingDialog> dialog; // 每一个activity中都需要一个dialog的实例

    @Inject
    protected YTimeApp app; // 上下文对象

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 动画切换
        // overridePendingTransition(R.anim.fade_in, R.anim.fade_out1);
        super.onCreate(savedInstanceState);

        // 设置屏幕的状态，默认竖屏
        setScreenStatus();

        // 设置布局
        setContentView(getLayoutId());

        // 添加到堆栈管理
        AppManager.getAppManager().addActivity(this);

        // 依赖注入
        ActivityComponent dagger = DaggerActivityComponent.builder()
                .appComponent(YTimeApp.sAppComponent)
                .activityModule(new ActivityModule(this))
                .build();

        inject(dagger);

        // 绑定presenter 对象
        presenter.attachView(this);

        // 查找控件额类
        ButterKnife.bind(this);

        // 子类回调初始化
        afterCreate();
    }

    /**
     * 设置屏幕状态
     */
    public void setScreenStatus() {
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    /**
     * 初始化一些配置
     */
    protected abstract void afterCreate();

    protected abstract void inject(ActivityComponent dagger);

    public abstract int getLayoutId();

    @Override
    public void showLoading(String msg) {
        if (!dialog.get().isShowing()) {
            dialog.get().show();
        }
    }

    @Override
    public void hideLoading() {
        if (dialog.get() != null && dialog.get().isShowing()) {
            dialog.get().dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public YTimeApp getApp() {
        return app;
    }

    @Override
    public void intent2Activity(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    @Override
    public void intent2Activity(Class cls, Bundle budle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(budle);
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        // 动画特效
        // overridePendingTransition(0, R.anim.fade_out);
    }

    @Override
    public void intentActivityForResult(Class cls, Bundle bundle, int requestData) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestData);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter = null;
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void log(String msg) {
        Log.i(TAG, msg);
    }
}
