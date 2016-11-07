package com.spy.spycamera.presentation.dependencyInjection.modules;

import android.content.Context;

import com.spy.spycamera.presentation.MyApp;
import com.spy.spycamera.presentation.util.Navigator;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Duc Nguyen
 * @version 1.0
 * @since 11/7/16
 */
@Module
public class ApplicationModule {
    MyApp myApp;
    public ApplicationModule(MyApp myApp) {
        this.myApp = myApp;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext(){
        return myApp;
    }

    @Provides
    @Singleton
    public Navigator provideNavigator(){
        return new Navigator();
    }

}
