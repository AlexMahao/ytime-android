package com.spearbothy.ytime.dagger;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by alex_mahao on 2016/8/3.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
