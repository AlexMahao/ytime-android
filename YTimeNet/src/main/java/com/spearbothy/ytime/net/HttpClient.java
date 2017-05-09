package com.spearbothy.ytime.net;

import com.google.gson.Gson;

/**
 * 网络请求的操作
 * Created by mahao on 17-5-9.
 */
public class HttpClient<T> {

    private HttpRequest<T> mRequest;

    public HttpClient(HttpRequest<T> mRequest) {
        this.mRequest = mRequest;
    }

    public void execute(final HttpResult<T> result) {
        HttpUtils.sINetAdapter.execute(mRequest, new HttpResult<String>() {
            @Override
            public void onSuccess(String data) {
                try {
                    T t = HttpUtils.sIParser.fromJson(data, mRequest.gettClass());
                    result.onSuccess(t);
                } catch (Exception e) {
                    result.onError(PARSE_DATA_ERROR, e.getMessage());
                }
            }

            @Override
            public void onError(int code, String msg) {
                result.onError(code, msg);
            }
        });
    }
}
