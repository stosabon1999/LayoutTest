package ru.production.ssobolevsky.layouttest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static ru.production.ssobolevsky.layouttest.MainActivity.STRINGS;

public class MyService extends Service {

    public static final String COLORS_THREE = "COLORSTHREE";

    public static final String TEXTS_THREE = "TEXTSTHREE";

    public static final int MSG_REGISTER_CLIENT = 1001;

    public static final int MSG_UNREGISTER_CLIENT = 1002;

    public static final int MSG_SEND_DATA = 1003;

    private final static int MODE = START_NOT_STICKY;


    private List<Messenger> mClients = new ArrayList<>();

    private Messenger mMessenger = new Messenger(new IncomingHandler());

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10) {
                Message msg = Message.obtain(null, MSG_SEND_DATA);
                Bundle bundle = new Bundle();
                bundle.putIntArray(COLORS_THREE, getRandomColors());
                bundle.putStringArray(TEXTS_THREE, getRandomTexts());
                msg.setData(bundle);
                for (Messenger messenger : mClients) {
                    try {
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                i++;
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return MODE;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT :
                    mClients.add(msg.replyTo);
                    break;
                case MSG_UNREGISTER_CLIENT :
                    mClients.remove(msg.replyTo);
                    break;
                case MSG_SEND_DATA :
                    break;
            }
        }
    }

    private int[] getRandomColors() {
        int i = 0;
        int[] colors = new int[9];
        while (i < 10) {
            int j = 0;
            while (j < 9) {
                colors[j] = getRandomColor();
                j++;
            }
            i++;
        }
        return colors;
    }

    private int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    private String[] getRandomTexts() {
        int i = 0;
        String[] strings = new String[9];
        while (i < 10) {
            int j = 0;
            while (j < 9) {
                strings[j] = String.valueOf((int) (Math.random() * 100));
                j++;
            }
            i++;
        }
        return strings;
    }

    public static final Intent newInstance(Context context) {
        Intent intent = new Intent(context, MyService.class);
        return intent;
    }

}
