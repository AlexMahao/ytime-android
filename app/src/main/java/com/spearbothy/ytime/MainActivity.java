package com.spearbothy.ytime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.spearbothy.ytime.net.HttpResult;
import com.spearbothy.ytime.netimpl.entity.User;
import com.spearbothy.ytime.netimpl.request.RegisterRequest;
import com.spearbothy.ytime.netimpl.response.BaseResponse;

public class MainActivity extends AppCompatActivity {
    String url = "https://help.huli.com/element/androidbanner/index.json";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = ((TextView) findViewById(R.id.textView));
        requestContent();
    }

    private void requestContent() {
        new RegisterRequest()
                .setName("1235312321")
                .setPassword("12312312312")
                .execute(new HttpResult<BaseResponse<User>>() {
                    @Override
                    public void onSuccess(BaseResponse<User> data) {
                        mTextView.setText(data.toString());
                    }

                    @Override
                    public void onError(int code, String msg) {
                        mTextView.setText(msg);
                    }
                });

    }
}
