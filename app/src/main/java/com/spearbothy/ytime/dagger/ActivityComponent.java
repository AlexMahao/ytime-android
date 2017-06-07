package com.spearbothy.ytime.dagger;


import dagger.Component;

/**
 * Created by alex_mahao on 2016/8/3.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

}
