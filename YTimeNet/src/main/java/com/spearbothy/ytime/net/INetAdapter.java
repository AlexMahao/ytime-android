package com.spearbothy.ytime.net;

/**
 * 网络执行方法
 * Created by mahao on 17-5-9.
 */
public interface INetAdapter {

    void execute(HttpRequest request,HttpResult<String> httpResult);

     void cancel(String tag);
}
