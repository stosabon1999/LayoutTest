package ru.production.ssobolevsky.layouttest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static ru.production.ssobolevsky.layouttest.MainActivity.COLORS;
import static ru.production.ssobolevsky.layouttest.MainActivity.STRINGS;


public class MyIntentService2 extends IntentService {

    public MyIntentService2() {
        super("MyIntentService2");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int i = 0;
        while (i < 10) {
            int j = 0;
            String[] strings = new String[3];
            while (j < 3) {
                strings[j] = String.valueOf((int) (Math.random() * 100));
                j++;
            }
            Intent broadcastIntent = new Intent(MainActivity.FILTER_STRINGS);
            broadcastIntent.putExtra(STRINGS, strings);
            sendBroadcast(broadcastIntent);
            i++;
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MyIntentService2.class);
        return intent;
    }
}
