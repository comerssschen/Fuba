package cn.weipan.fb.service;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ProcessUtils;

import org.greenrobot.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;
import cn.weipan.fb.MainActivity;
import cn.weipan.fb.bean.AllMessagBean;
import cn.weipan.fb.common.FirstEvent;

/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "test";
    private PowerManager.WakeLock wakeLock;
    private PowerManager pm;

    //这是jpush接受消息的广播
    @SuppressLint("InvalidWakeLockTag")
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.i(TAG, "JPush用户注册成功");
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG, "接受到推送下来的自定义消息");
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            2|0.02|2017022221001004360290593533|2017/02/22 09:52:18
            if (message.split("\\|").length > 3) {
                String[] temp = null;
                temp = message.split("\\|");
                Log.i("test", "MyReceiver = text = " + message);
                if (message != null) {
                    if (temp.length > 3) {
                        if (temp[0].equals("1")) {
                            temp[0] = "微信支付";
                        } else if (temp[0].equals("2")) {
                            temp[0] = "支付宝";
                        } else if (temp[0].equals("3")) {
                            temp[0] = "银联";
                        } else if (temp[0].equals("9")) {
                            temp[0] = "翼支付";
                        } else {
                            temp[0] = "";
                        }
                        AllMessagBean allMessagBean = new AllMessagBean(temp[0] + "收款", temp[3], temp[2], temp[1]);//标题，时间，单号，类型
                        EventBus.getDefault().post(new FirstEvent(allMessagBean));
                    }
                }
            }
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.i(TAG, "接受到推送下来的通知");
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_ALERT);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.i(TAG, " title : " + title + ",extras : " + extras + ",message : " + message);
            if (pm == null) {
                pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            }
            if (wakeLock == null) {
                wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP
                        | PowerManager.SCREEN_DIM_WAKE_LOCK, "WakeAndLock");
            }
            if (!pm.isScreenOn()) {
                wakeLock.acquire();
                wakeLock.release();
            }
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.i(TAG, "用户点击打开了通知");
            Log.i(TAG, "isAppForeground:" + AppUtils.isAppForeground());//true  false false
            Log.i(TAG, "isMainProcess:" + ProcessUtils.isMainProcess());//true  true true
            Log.i(TAG, "getCurrentProcessName:" + ProcessUtils.getCurrentProcessName());//com.weipan.dailypay  com.weipan.dailypay

            Intent intentActivity = new Intent(context, MainActivity.class);
            intentActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentActivity);

        } else {
            Log.i(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}