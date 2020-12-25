
package com.hozan.applets;

import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;


public class RNAppletsModule extends ReactContextBaseJavaModule implements IWXAPIEventHandler {

    private final ReactApplicationContext reactContext;
    private String TAG = "RNAppletsModule";
    private String appId;

    private IWXAPI api = null;
    private final static String NOT_REGISTERED = "registerApp required.";
    private final static String INVOKE_FAILED = "WeChat API invoke returns false.";
    private final static String INVALID_ARGUMENT = "invalid argument.";

    public RNAppletsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNApplets";
    }

    private static ArrayList<RNAppletsModule> modules = new ArrayList<>();

    @Override
    public void initialize() {
        super.initialize();
        modules.add(this);
    }
    @Override
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        if (api != null) {
            api = null;
        }
        modules.remove(this);
    }

    public static void handleIntent(Intent intent) {
        for (RNAppletsModule mod : modules) {
            mod.api.handleIntent(intent, mod);
        }
    }


    /**
     * 注册appid-微信开发平台上申请应用
     * @param appid
     * @param callback
     */
    @ReactMethod
    public void registerApp(String appid, Callback callback) {
        this.appId = appid;
        api = WXAPIFactory.createWXAPI(this.reactContext, appid, true);
        callback.invoke( api.registerApp(appid));
    }

    @ReactMethod
    public void isWXAppInstalled(Callback callback) {
        if (api == null) {
            callback.invoke(NOT_REGISTERED);
            return;
        }
        callback.invoke( api.isWXAppInstalled());
    }

    /**
     * 打开微信
     * @param callback
     */
    @ReactMethod
    public void openWXApp(Callback callback) {
        if (api == null) {
            callback.invoke(NOT_REGISTERED);
            return;
        }
        callback.invoke( api.openWXApp());
    }

    /**
     * 调起微信小程序
     * @param data
     * @param callback
     */
    @ReactMethod
    public void launchMini(ReadableMap data, Callback callback) {
        if (api == null) {
            callback.invoke(NOT_REGISTERED);
            return;
        }
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = data.getString("userName"); // 填小程序原始id
        req.path = data.getString("path");                  //拉起小程序页面的可带参路径，不填默认拉起小程序首页
        req.miniprogramType = data.getInt("miniProgramType");// 可选打开 开发版，体验版和正式版
        boolean success = api.sendReq(req);
        if (!success) callback.invoke(INVALID_ARGUMENT);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    /**
     * 微信相关回调
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        WritableMap map = Arguments.createMap();
        map.putInt("errCode", baseResp.errCode);
        map.putString("errStr", baseResp.errStr);
        map.putString("openId", baseResp.openId);
        map.putString("transaction", baseResp.transaction);

        if (baseResp.getType() == ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM) {
            WXLaunchMiniProgram.Resp resp = (WXLaunchMiniProgram.Resp) baseResp;
            String extraData = resp.extMsg;
            map.putString("extraData", extraData);
            Log.d(TAG, "onResp: "+extraData);
        }

        this.getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("WeChat_Resp", map);
    }

}