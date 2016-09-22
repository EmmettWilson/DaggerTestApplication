package com.mathematicalfunk.daggertest;

import android.content.Context;
import android.content.Intent;

import static android.os.SystemClock.sleep;

public class UatBase {

    public static final UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    protected static void launchLauncher() {
        // Start from the home screen
        device.pressHome();

        // Wait for launcher
        final String launcherPackage = device.getLauncherPackageName();
        assertNotNull(launcherPackage);
    }

    protected static void launchApp(String applicationId) {
        Context context = InstrumentationRegistry.getContext();

        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(applicationId);
        if (intent == null) {
            fail("Could not launch app " + applicationId + " Did you install it?");
        }

        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        sleep(10000);
    }

    @Before
    public void startApplicationFromHomeScreen() {
        launchLauncher();
        launchApp(BuildConfig.APPLICATION_ID);
    }
}
