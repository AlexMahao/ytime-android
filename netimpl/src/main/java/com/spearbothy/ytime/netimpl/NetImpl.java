package com.spearbothy.ytime.netimpl;

import com.spearbothy.ytime.net.HttpUtils;
import com.spearbothy.ytime.net.adapter.GsonParserAdapter;
import com.spearbothy.ytime.net.adapter.OkHttpAdapter;

/**
 * Created by Alex_MaHao on 2017/5/31.
 */
public class NetImpl {

    public static void init(){
        HttpUtils.setsIParser(new GsonParserAdapter());
        HttpUtils.setHttpClientAdapter(new OkHttpAdapter());
    }
}
