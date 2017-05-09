package com.spearbothy.ytime.net;

/**
 * 网络请求监听的回调
 * Created by mahao on 17-5-9.
 */
public interface HttpResult<T> {

    int NET_ERROR = 1;

    int PARSE_DATA_ERROR = 2;

    void onSuccess(T data);

    void onError(int code,String msg);
}
