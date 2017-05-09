package com.spearbothy.ytime.net;

import com.google.gson.JsonArray;
import com.spearbothy.ytime.net.adapter.GsonParserAdapter;
import com.spearbothy.ytime.net.adapter.HttpUrlConnectionAdapter;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    String url = "https://help.huli.com/element/androidbanner/index.json";
    @Test
    public void addition_isCorrect() throws Exception {
        // 设置网络连接
        HttpUtils.setHttpClientAdapter(new HttpUrlConnectionAdapter());
        // 设置数据解析
        HttpUtils.setsIParser(new GsonParserAdapter());
/*
        HttpUtils.getRequest(new ArrayList<Bean>())
                .setUrl(url)
                .setMethod(HttpMethod.GET)
                .build()
                .execute(new HttpResult<ArrayList<Bean>>() {
                    @Override
                    public void onSuccess(ArrayList<Bean> data) {
                        System.out.println(data.toString());
                    }

                    @Override
                    public void onError(int code) {
                        System.out.println(code);
                    }
                });
*/
        HttpUtils.getRequest(new JSONArray())
                .setUrl(url)
                .setMethod(HttpMethod.GET)
                .build()
                .execute(new HttpResult<JSONArray>() {
                    @Override
                    public void onSuccess(JSONArray data) {
                        System.out.println(data.toString());
                    }

                    @Override
                    public void onError(int code,String msg) {
                        System.out.println(code+"---"+msg);
                    }
                });

    }
}