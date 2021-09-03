package com.example.restapiproject;

import android.content.Context;
import android.content.Intent;

public class IntentFactory {
    public static Intent getIntent(String className,
                                   Context context,
                                   String val1,
                                   String val2) {
        switch (className) {
            case "LandingActivity":
                return LandingActivity.getIntent(context, val1, val2);
            default:
                return new Intent(context, MainActivity.class);
        }
    }
}
