package com.spearbothy.ytime.net;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据请求
 * Created by mahao on 17-5-9.
 */
public class HttpRequest<T> {
    private Map<String, String> mHeaderParams = new HashMap<>();
    private Map<String, String> mParams = new HashMap<>();
    private HttpMethod mMethod = HttpMethod.GET;
    private String url;
//    private T mResponse;
    private String mTag; // 取消任务
    private Class<T> tClass;

    public HttpRequest(Class<T> mResponse) {
//        this.mResponse = mResponse;
        this.tClass = mResponse;
    }

//    public T getResponse() {
//        return mResponse;
//    }

    public Class<T> gettClass() {
        return tClass;
    }

    public HttpRequest<T>  setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String mTag) {
        this.mTag = mTag;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaderParams() {
        return mHeaderParams;
    }

    public Map<String, String> getParams() {
        return mParams;
    }

    public HttpMethod getMethod() {
        return mMethod;
    }

    public HttpRequest<T> addHeader(String key, String value){
        mHeaderParams.put(key,value);
        return this;
    }

    public HttpRequest<T> addHeaders(Map<String,String> headers){
        mHeaderParams.putAll(headers);
        return this;
    }

    public HttpRequest<T> addParam(String key,String value){
        mParams.put(key,value);
        return this;
    }
    public HttpRequest<T> addParams(Map<String,String> params){
        mParams.putAll(params);
        return this;
    }

    public HttpRequest<T> setMethod(HttpMethod method){
        mMethod = method;
        return this;
    }

   public HttpClient<T> build(){
       return new HttpClient<T>(this);
   }

}
