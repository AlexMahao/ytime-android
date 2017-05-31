package com.spearbothy.ytime.net;

/**
 *
 *  实例化HttpClient,保证client唯一
 * Created by mahao on 17-5-9.
 */
public class HttpUtils {

    public static INetAdapter sINetAdapter;

    public static IParser sIParser;

    public static void setHttpClientAdapter(INetAdapter client){
        sINetAdapter = client;
    }

    public static void setsIParser(IParser IParser) {
        sIParser = IParser;
    }

    public static <T> HttpRequest<T> getRequest(Class<T> response){
        return new HttpRequest<T>(response);
    }

    public static void  cancel(String tag){
        sINetAdapter.cancel(tag);
    }

}
