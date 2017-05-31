package com.spearbothy.ytime.netimpl;

import android.provider.Settings;

import com.spearbothy.ytime.net.HttpResult;
import com.spearbothy.ytime.netimpl.entity.User;
import com.spearbothy.ytime.netimpl.request.RegisterRequest;
import com.spearbothy.ytime.netimpl.response.BaseResponse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Before
    public void init(){
        NetImpl.init();
    }

    @Test
    public void addition_isCorrect() throws Exception {
        new RegisterRequest()
                .setName("1235312321")
                .setPassword("12312312312")
                .execute(new HttpResult<BaseResponse<User>>() {
                    @Override
                    public void onSuccess(BaseResponse<User> data) {
                        System.out.println(data.toString());
                    }

                    @Override
                    public void onError(int code, String msg) {
                        System.out.println(msg);
                    }
                });
    }
}