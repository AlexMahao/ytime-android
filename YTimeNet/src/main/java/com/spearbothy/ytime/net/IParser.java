package com.spearbothy.ytime.net;

/**
 * Created by mahao on 17-5-9.
 */

public interface IParser {

    String toJson(Object o);

    <T> T fromJson(String data, Class<T> tClass) throws Exception;
}
