package com.spy.spycamera.presentation.dependencyInjection.components;

import com.spy.spycamera.presentation.dependencyInjection.modules.ApplicationModule;
import com.spy.spycamera.presentation.ui.activities.MainActivity;
import com.spy.spycamera.presentation.ui.fragments.ForegroundCameraFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Duc Nguyen
 * @version 1.0
 * @since 11/7/16
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity baseActivity);
    void inject(ForegroundCameraFragment baseFragment);
}
