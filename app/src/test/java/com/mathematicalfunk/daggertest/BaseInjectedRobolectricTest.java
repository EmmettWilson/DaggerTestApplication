package com.mathematicalfunk.daggertest;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import it.cosenonjaviste.daggermock.DaggerMockRule;

import static android.os.Build.VERSION_CODES.LOLLIPOP;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = LOLLIPOP, application = TestApp.class)
public abstract class BaseInjectedRobolectricTest {

    @Rule public DaggerMockRule<ApplicationComponent> daggerMockRule = new DaggerMockRule<>(ApplicationComponent.class, new ApplicationModule(((TestApp) RuntimeEnvironment.application)))
            .set(new DaggerMockRule.ComponentSetter<ApplicationComponent>() {
                @Override
                public void setComponent(ApplicationComponent applicationComponent) {
                    ((TestApp) RuntimeEnvironment.application).setComponent(applicationComponent);
                }
            });
}
