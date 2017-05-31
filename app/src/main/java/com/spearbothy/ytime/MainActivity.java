package com.spearbothy.ytime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.spearbothy.ytime.net.HttpMethod;
import com.spearbothy.ytime.net.HttpResult;
import com.spearbothy.ytime.net.HttpUtils;

import java.util.ArrayList;

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
        HttpUtils.getRequest(new ArrayList<Bean>())
                .setUrl(url)
                .setMethod(HttpMethod.GET)
                .build()
                .execute(new HttpResult<ArrayList<Bean>>() {
                    @Override
                    public void onSuccess(ArrayList<Bean> data) {
                        mTextView.setText(data.toString());
                    }

                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(MainActivity.this, code+":"+msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
