package com.spearbothy.ytime.netimpl.request;

import com.spearbothy.ytime.net.HttpMethod;
import com.spearbothy.ytime.net.HttpResult;
import com.spearbothy.ytime.net.HttpUtils;
import com.spearbothy.ytime.netimpl.Urls;
import com.spearbothy.ytime.netimpl.YTimeUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex_MaHao on 2017/5/31.
 */
public abstract class BaseRequest<T> implements Request<T> {

    private Map<String,String> mHeaderParams = new HashMap<>();

    public void execute(HttpResult<T> result){
        HttpUtils.getRequest(getResponseClass())
                .setUrl(getUrl())
                .addParams(getParams())
                .addHeaders(getHeaderParams())
                .setMethod(getMethod())
                .build()
                .execute(result);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }

    @Override
    public Map<String, String> getHeaderParams() {
        return mHeaderParams;
    }

    @Override
    public Map<String, String> getParams() {
        return YTimeUtils.object2Map(this);
    }
}
