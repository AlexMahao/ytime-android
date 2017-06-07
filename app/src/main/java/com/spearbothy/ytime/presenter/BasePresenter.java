package com.spearbothy.ytime.presenter;

import android.content.Intent;

import com.spearbothy.ytime.netimpl.request.Request;
import com.spearbothy.ytime.view.IView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alex_mahao on 2016/8/3.
 */
public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T view;

    protected List<Request> mRequestList = new ArrayList<>();

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        //取消网络请求
        for (Request request : mRequestList) {
            request.cancel();
        }
        view = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {}
}
