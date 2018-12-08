package com.superhao.react_native_mob_sms;

import android.app.Activity;
import android.content.Context;

import com.mob.MobSDK;

public class MobSMS {

    public static void init(final Context context, String appkey, String appSecret) {
        MobSDK.init(context, appkey, appSecret);
    }
}
