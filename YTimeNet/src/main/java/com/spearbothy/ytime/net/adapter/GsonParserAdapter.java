package com.spearbothy.ytime.net.adapter;

import com.google.gson.Gson;
import com.spearbothy.ytime.net.GsonUtil;
import com.spearbothy.ytime.net.IParser;

/**
 * Created by mahao on 17-5-9.
 */

public class GsonParserAdapter implements IParser {

    @Override
    public String toJson(Object o) {
        return GsonUtil.GsonString(o);
    }

    @Override
    public <T> T fromJson(String data, Class<T> tClass) throws Exception {
        return GsonUtil.GsonToBean(data,tClass);
    }
}
