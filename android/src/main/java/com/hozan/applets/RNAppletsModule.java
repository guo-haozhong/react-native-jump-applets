
package com.hozan.applets;

import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONObject;


public class RNAppletsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private String Tag = "RNAppletsModule";

    public RNAppletsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @ReactMethod
    public void callNative(ReadableMap params, Promise promise) {
        Log.i(Tag, "收到RN指令：" + params);
        String action = params.getString("action");
        String data = params.getString("data");

        try {
            JSONObject obj = new JSONObject(data);
            Log.i(Tag, obj+"");
            switch (action) {
                case "jumpApplets":
                    String appId = obj.getString("appId"); // 填应用AppId
                    String userName = obj.getString("userName");
                    String path = obj.getString("path");
                    int miniprogramType = obj.getInt("miniprogramType");
                    IWXAPI api = WXAPIFactory.createWXAPI(reactContext, appId);

                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = userName; // 填小程序原始id
                    req.path = path;   ///pages/minipropay/minipropay? 拉起小程序页面的可带参路径，不填默认拉起小程序首页，对于小游戏，可以只传入 query 部分，来实现传参效果，如：传入 "?foo=bar"。
                    req.miniprogramType = miniprogramType;// 可选打开 开发版，体验版和正式版 WXLaunchMiniProgram.Req.MINIPROGRAM_TYPE_TEST
                    api.sendReq(req);
//                    WritableMap writableMap= Arguments.createMap();
//                    writableMap.putString("result","success");
//                    promise.resolve(writableMap);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            promise.reject("失败提示", e);
        }


    }

    @Override
    public String getName() {
        return "RNApplets";
    }
}