package ru.production.ssobolevsky.layouttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by pro on 21.06.2018.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    private ViewCallBack mViewCallBack;

    public MyBroadcastReceiver(ViewCallBack viewCallBack) {
        mViewCallBack = viewCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case MainActivity.FILTER_STRINGS :
                mViewCallBack.onTextReceived(intent.getStringArrayExtra(MainActivity.STRINGS));
                break;
            case MainActivity.FILTER_COLORS:
                mViewCallBack.onColorsReceived(intent.getIntArrayExtra(MainActivity.COLORS));
                break;
            default:
                break;
        }

    }
}
