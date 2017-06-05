package com.spearbothy.ytime.netimpl.request;

import com.spearbothy.ytime.netimpl.Urls;
import com.spearbothy.ytime.netimpl.entity.User;
import com.spearbothy.ytime.netimpl.response.BaseResponse;

/**
 * Created by Alex_MaHao on 2017/5/31.
 */
public class RegisterRequest extends BaseRequest<BaseResponse<User>> {

    private String name;

    private String password;


    public String getName() {
        return name;
    }

    public RegisterRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public Class<BaseResponse<User>> getResponseClass() {
        return (Class<BaseResponse<User>>) new BaseResponse<User>().getClass();
    }

    @Override
    public String getUrl() {
        return Urls.USER_REGISTER_NAME;
    }
}
