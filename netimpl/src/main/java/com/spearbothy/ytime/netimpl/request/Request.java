package com.spearbothy.ytime.netimpl.request;

import com.spearbothy.ytime.net.HttpMethod;

import java.util.Map;

/**
 * Created by Alex_MaHao on 2017/5/31.
 */
public interface Request<T> {


    Class<T> getResponseClass();

    String getUrl();

    HttpMethod getMethod();

    Map<String,String> getParams();

    Map<String,String> getHeaderParams();

}
