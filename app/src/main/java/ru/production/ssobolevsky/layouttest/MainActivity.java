package ru.production.ssobolevsky.layouttest;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements IActivityCallback {

    public static final String FILTER_COLORS = "SOBOLEVSKY";

    public static final String FILTER_STRINGS = "SOBOLEVSKY2";

    public static final String COLORS = "COLORS";

    public static final String STRINGS = "STRINGS";

    private MyBroadcastReceiver mBroadcastReceiver;

    private IntentFilter mIntentFilter;

    private IntentFilter mIntentFilter2;

    private Messenger mService;

    private Messenger mService2;

    final Messenger mMessenger = new Messenger(new IncomingHandler());

    final Messenger mMessenger2 = new Messenger(new IncomingHandler2());

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = new Messenger(iBinder);
            Message msg = Message.obtain(null, MyService.MSG_REGISTER_CLIENT);
            msg.replyTo = mMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            startService(MyService.newInstance(MainActivity.this));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
        }
    };

    private ServiceConnection mServiceConnection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService2 = new Messenger(iBinder);
            Message msg = Message.obtain(null, MyService2.MSG_REGISTER_CLIENT);
            msg.replyTo = mMessenger2;
            try {
                mService2.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            startService(MyService2.newInstance(MainActivity.this));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService2 = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(MyIntentService.newInstance(MainActivity.this));
        startService(MyIntentService2.newInstance(MainActivity.this));
        mBroadcastReceiver = new MyBroadcastReceiver(new ViewCallBackImpl());
        mIntentFilter = new IntentFilter(FILTER_COLORS);
        mIntentFilter2 = new IntentFilter(FILTER_STRINGS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mBroadcastReceiver, mIntentFilter);
        registerReceiver(mBroadcastReceiver, mIntentFilter2);
        bindServices();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
        unbindServices();
    }

    public void bindServices() {
        bindService(MyService.newInstance(MainActivity.this), mServiceConnection, Context.BIND_AUTO_CREATE);
        bindService(MyService2.newInstance(MainActivity.this), mServiceConnection2, Context.BIND_AUTO_CREATE);
    }

    private void unbindServices() {
        Message msg = Message.obtain(null, MyService.MSG_UNREGISTER_CLIENT);
        msg.replyTo = mMessenger;
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unbindService(mServiceConnection);
        Message msg2 = Message.obtain(null, MyService2.MSG_UNREGISTER_CLIENT);
        msg.replyTo = mMessenger2;
        try {
            mService2.send(msg2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unbindService(mServiceConnection2);
    }

    @Override
    public void onFragmentData(String text) {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.fragment_three);
        ((FragmentThree) fragment).setButtonText(text);
    }

    private class ViewCallBackImpl implements ViewCallBack {

        @Override
        public void onColorsReceived(int[] colors) {
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_one);

            ((FragmentOne) fragment).setButtonsColors(colors);
        }

        @Override
        public void onTextReceived(String[] strings) {
            Fragment fragment = getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_three);

            ((FragmentThree) fragment).setButtonsTexts(strings);
        }

    }

    private class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyService.MSG_SEND_DATA:
                    Fragment fragment = getSupportFragmentManager()
                            .findFragmentById(R.id.fragment_two);
                    ((FragmentTwo) fragment).setTextAndColors(msg.getData().getIntArray(MyService.COLORS_THREE), msg.getData().getStringArray(MyService.TEXTS_THREE));
                    break;
            }
        }
    }

    private class IncomingHandler2 extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MyService2.MSG_SEND_DATA :
                    Fragment fragment = getSupportFragmentManager()
                            .findFragmentById(R.id.fragment_four);
                    ((FragmentFour) fragment).setCornerAndTexts(msg.getData().getInt(MyService2.CORNER), msg.getData().getStringArray(MyService2.TEXTS_FOUR));
                    break;
            }
        }
    }
}
