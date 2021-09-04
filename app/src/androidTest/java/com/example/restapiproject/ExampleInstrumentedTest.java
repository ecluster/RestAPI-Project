package com.example.restapiproject;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;


import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.restapiproject", appContext.getPackageName());
    }

    @Test
    public void getIntentTest() {
        Context context = getInstrumentation().getTargetContext();
        Intent i = LandingActivity.getIntent(context, "1", "edward");
        i.putExtra("idLabel", "1");
        assertEquals("1", i.getStringExtra("idLabel"));

    }

}