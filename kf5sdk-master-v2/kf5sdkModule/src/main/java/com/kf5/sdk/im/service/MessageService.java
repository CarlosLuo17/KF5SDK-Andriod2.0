package com.kf5.sdk.im.service;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kf5Engine.service.BaseService;
import com.kf5Engine.service.api.ActionCallBack;
import com.kf5Engine.service.api.SocketEventCallBack;

/**
 * author:chosen
 * date:2016/10/27 15:08
 * email:812219713@qq.com
 */

public class MessageService extends BaseService {


    private MessageServiceStub mMessageServiceStub;

    @Override
    public void onCreate() {
        super.onCreate();
        mMessageServiceStub = new MessageServiceStub(this);
        Log.i("=====", "服务创建");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("====", "服务绑定");
        return mMessageServiceStub;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMessageServiceStub.mCallbackList.kill();
    }

    @Override
    protected SocketEventCallBack getSocketEventCallBack() {
        return new ConnectionEvent(mMessageServiceStub.mCallbackList);
    }

    /**
     * 初始化IM必要参数
     *
     * @param bundle
     */
    public void initParams(Bundle bundle) {
        initSocket(bundle);
    }

    /**
     * 创建连接
     */
    public void createConnect() {
        connect();
    }

    /**
     * 是否连接
     *
     * @return
     */
    public boolean isConnectionConnected() {
        return isConnected();
    }

    /**
     * 断开连接
     */
    public void offConnect() {
        disconnect();
    }

    /**
     * 发送指令
     *
     * @param actionCallBack
     * @param objects
     */
    public void sendEventRequest(final ActionCallBack actionCallBack, Object... objects) {
        sendRequest(actionCallBack, objects);
    }

}
