package com.spearbothy.ytime.net;

/**
 * 网络请求的操作
 * Created by mahao on 17-5-9.
 */
public class HttpClient<T> {

    private HttpRequest<T> mRequest;

    private boolean isRun = false;

    public HttpClient(HttpRequest<T> mRequest) {
        this.mRequest = mRequest;
    }

    public HttpClient<T> execute(final HttpResult<T> result) {
        if (isRun) {
            return this;
        }
        isRun = true;
        HttpUtils.sINetAdapter.execute(mRequest, new HttpResult<String>() {
            @Override
            public void onSuccess(String data) {
                isRun = false;
                try {
                    T t = HttpUtils.sIParser.fromJson(data, mRequest.gettClass());
                    result.onSuccess(t);
                } catch (Exception e) {
                    result.onError(PARSE_DATA_ERROR, e.getMessage());
                }
            }

            @Override
            public void onError(int code, String msg) {
                isRun = false;
                result.onError(code, msg);
            }
        });
        return this;
    }

    /**
     * 取消任务
     */
    public void cancel() {
        if (!isRun) {
            return;
        }
        HttpUtils.cancel(mRequest.getTag());
        isRun = false;
    }
}
