package com.spearbothy.ytime.presenter;


import android.content.Intent;

import com.spearbothy.ytime.view.IView;

/**
 * Presenter 的基类
 * <p>
 * Created by alex_mahao on 2016/8/3.
 */
public interface IPresenter<T extends IView> {

    // 绑定View
    void attachView(T view);

    // 解绑View  防止内存溢出，及始终持有Activity
    void detachView();

    // activity result回调
    void onActivityResult(int requestCode, int resultCode, Intent data);

}
