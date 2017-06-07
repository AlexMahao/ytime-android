package com.spearbothy.ytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spearbothy.ytime.dagger.ActivityComponent;
import com.spearbothy.ytime.dagger.ActivityModule;
import com.spearbothy.ytime.dagger.DaggerActivityComponent;
import com.spearbothy.ytime.presenter.IPresenter;
import com.spearbothy.ytime.view.IView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by mahao on 17-6-7.
 */

public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView {

    private String TAG = getClass().getSimpleName();

    @Inject
    public T presenter; // presenter对象

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 绑定presenter 对象
        presenter.attachView(this);

        ActivityComponent dagger = DaggerActivityComponent.builder()
                .appComponent(YTimeApp.sAppComponent)
                .activityModule(new ActivityModule(getActivity()))
                .build();

        inject(dagger);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        afterCreate();
    }

    protected abstract void afterCreate();

    protected abstract void inject(ActivityComponent dagger);

    protected abstract int getLayoutId();

    @Override
    public void showLoading(String msg) {
        ((IView) getActivity()).showLoading(msg);
    }

    @Override
    public void hideLoading() {
        ((IView) getActivity()).hideLoading();
    }

    @Override
    public void showToast(String msg) {
        ((IView) getActivity()).showToast(msg);
    }

    @Override
    public void intent2Activity(Class cls) {
        Intent intent = new Intent(getContext(), cls);
        startActivity(intent);
    }

    @Override
    public YTimeApp getApp() {
        return ((IView) getActivity()).getApp();
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void intent2Activity(Class cls, Bundle bundle) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void intentActivityForResult(Class cls, Bundle bundle, int requestData) {
        Intent intent = new Intent(getContext(), cls);
        intent.putExtras(bundle);
        startActivityForResult(intent, requestData);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter = null;
    }

    @Override
    public void log(String msg) {
        Log.i(TAG, msg);
    }
}
