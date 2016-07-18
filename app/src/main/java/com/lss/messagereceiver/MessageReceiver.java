package com.lss.messagereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by shuai on 16-7-18.
 */
public class MessageReceiver extends BroadcastReceiver {
    private static final String TAG = "MessageReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取所有的短信信息
        Object[] objs = (Object[]) intent.getExtras().get("pdus");
        for (Object obj:objs){
            //把pdu中的对象转化成SmsMessage对象
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String body = smsMessage.getMessageBody();
            String sender = smsMessage.getOriginatingAddress();
            Log.d(TAG, "onReceive: "+sender);
            Log.d(TAG, "onReceive: "+body);
            if ("10086".equals(sender)){
                Log.d(TAG, "拦截短信"+sender+body);
                abortBroadcast();
            }
        }
    }
}
