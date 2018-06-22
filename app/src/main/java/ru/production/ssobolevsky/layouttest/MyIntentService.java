package ru.production.ssobolevsky.layouttest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static ru.production.ssobolevsky.layouttest.MainActivity.COLORS;


public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            int j = 0;
            int[] colors = new int[3];
            while (j < 3) {
                colors[j] = getRandomColor();
                j++;
            }
            Intent broadcastIntent = new Intent(MainActivity.FILTER_COLORS);
            broadcastIntent.putExtra(COLORS, colors);
            sendBroadcast(broadcastIntent);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MyIntentService.class);
        return intent;
    }

}
