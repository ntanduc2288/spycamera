package com.spy.spycamera.presentation;

import android.app.Application;

import com.spy.spycamera.presentation.dependencyInjection.components.ApplicationComponent;
import com.spy.spycamera.presentation.dependencyInjection.components.DaggerApplicationComponent;
import com.spy.spycamera.presentation.dependencyInjection.modules.ApplicationModule;

/**
 * @author Duc Nguyen
 * @version 1.0
 * @since 11/7/16
 */
public class MyApp extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeInjector();
    }

    private void initializeInjector() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }
}
