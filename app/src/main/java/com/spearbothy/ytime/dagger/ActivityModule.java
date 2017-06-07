package com.spearbothy.ytime.dagger;

import android.app.Activity;

import com.spearbothy.ytime.widget.HintDialog;
import com.spearbothy.ytime.widget.LoadingDialog;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex_mahao on 2016/8/3.
 */
@Module
public class ActivityModule {

    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    LoadingDialog provideLoadingDialog() {
        return new LoadingDialog(activity);
    }

    @PerActivity
    @Provides
    HintDialog provideHintDialog() {
        return new HintDialog(activity);
    }
}
