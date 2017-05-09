package com.spearbothy.ytime.net.adapter;

import com.spearbothy.ytime.net.HttpMethod;
import com.spearbothy.ytime.net.HttpRequest;
import com.spearbothy.ytime.net.HttpResult;
import com.spearbothy.ytime.net.INetAdapter;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mahao on 17-5-9.
 */

public class HttpUrlConnectionAdapter implements INetAdapter {
    // 网络请求具体实现
    @Override
    public void execute(HttpRequest request, HttpResult<String> httpResult) {
        try {
            URL url = new URL(request.getUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(request.getMethod() == HttpMethod.GET){
                conn.setRequestMethod("GET");
            }else if(request.getMethod()==HttpMethod.POST){
                conn.setRequestMethod("POST");
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            if(conn.getResponseCode()==200){
                //得到输入流
                InputStream is =conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while(-1 != (len = is.read(buffer))){
                    baos.write(buffer,0,len);
                    baos.flush();
                }
                httpResult.onSuccess(baos.toString("utf-8"));
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        httpResult.onError(HttpResult.NET_ERROR,"网络错误");
    }

    @Override
    public void cancel(String tag) {
        // todo 取消任务
    }
}
