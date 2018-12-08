package com.superhao.react_native_mob_sms;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.mob.MobSDK;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class MobSMSModule extends ReactContextBaseJavaModule {

    private Boolean showToast;
    EventHandler eventHandler = new EventHandler() {
        public void afterEvent(int event, int result, Object data) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            Message msg = new Message();
            msg.arg1 = event;
            msg.arg2 = result;
            msg.obj = data;
            new Handler(getReactApplicationContext().getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    WritableMap map = Arguments.createMap();
                    map.putInt("event", event);
                    map.putInt("result", result);
                    getReactApplicationContext()
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit("getCode",map);
                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // TODO 处理成功得到验证码的结果
                            if (showToast) {
                                Toast.makeText(getCurrentActivity(), "发送成功", Toast.LENGTH_SHORT).show();
                            }
                            // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        } else {
                            if (showToast) {
                                Toast.makeText(getCurrentActivity(), "发送失败,错误码:" + result, Toast.LENGTH_SHORT).show();
                            }
                            // TODO 处理错误的结果
//                            ((Throwable) data).printStackTrace();
                        }
                    }
                    // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                    SMSSDK.unregisterEventHandler(eventHandler);
                    return false;
                }
            }).sendMessage(msg);
        }
    };

    public MobSMSModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ReactNativeMobSMS";
    }

    @ReactMethod
    public void init(String appkey, String appSecret) {
        MobSDK.init(getCurrentActivity(), appkey, appSecret);
    }

    @ReactMethod
    public void sendCode(String phone, String zone, String template, Boolean showToast){
        this.showToast = showToast;
        SMSSDK.registerEventHandler(eventHandler);
        if (template.length() == 0) {
            SMSSDK.getVerificationCode(zone, phone);
        } else {
            SMSSDK.getVerificationCode(template, zone, phone);
        }
    }


}
