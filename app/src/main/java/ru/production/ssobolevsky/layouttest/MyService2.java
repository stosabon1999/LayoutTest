package ru.production.ssobolevsky.layouttest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class MyService2 extends Service {

    public static final String TEXTS_FOUR = "TEXTSFOUR";

    public static final String CORNER = "CORNER";

    public static final int MSG_REGISTER_CLIENT = 10001;

    public static final int MSG_UNREGISTER_CLIENT = 10002;

    public static final int MSG_SEND_DATA = 10003;

    private final static int MODE = START_NOT_STICKY;

    private List<Messenger> mClients = new ArrayList<>();

    private Messenger mMessenger = new Messenger(new IncomingHandler2());

    public MyService2() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Message msg = Message.obtain(null, MSG_SEND_DATA);
                    Bundle bundle = new Bundle();
                    bundle.putInt(CORNER, (int) Math.random() * 90);
                    bundle.putStringArray(TEXTS_FOUR, getRandomTexts());
                    msg.setData(bundle);
                    for (Messenger messenger : mClients) {
                        try {
                            messenger.send(msg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
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

    public static final Intent newInstance(Context context) {
        Intent intent = new Intent(context, MyService2.class);
        return intent;
    }

    private class IncomingHandler2 extends Handler {
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

    private String[] getRandomTexts() {
        int i = 0;
        String[] strings = new String[3];
        while (i < 3) {
            strings[i] = String.valueOf((int) (Math.random() * 122000));
            i++;
        }
        return strings;
    }
}
