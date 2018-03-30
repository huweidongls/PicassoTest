package com.example.a99zan.picassotest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.squareup.picasso.Picasso;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 99zan on 2018/1/8.
 */

public class MyService extends Service {

    MyBinder myBinder = new MyBinder();

    private PowerManager.WakeLock mWakeLock;

    private PlayerListener playerListener;

    public void setOnPlayerListener(PlayerListener playerListener) {
        this.playerListener = playerListener;
    }

    public boolean isPlay (){
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        acquireWakeLock();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("111", "onStartCommand");
//        jcVideoPlayerStandard.setUp("http://www.jmzsjy.com/UploadFile/微课/地方风味小吃——宫廷香酥牛肉饼.mp4"
//                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "视频播放");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(playerListener!=null){
                    playerListener.play(true);
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    public void acquireWakeLock() {
        if (mWakeLock == null) {
            PowerManager pm = (PowerManager)this.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "ZHENGYI.WZY");
            if (mWakeLock != null) {
                mWakeLock.acquire();
                Log.e("wangzhengyi", "get powermanager wakelock!");
            }
        }
    }

    public void releaseWakeLock() {
        if (mWakeLock != null) {
            mWakeLock.release();
            Log.e("wangzhengyi", "release powermanager wakelock!");
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        releaseWakeLock();
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            super.run();
            Log.e("111", "thread方法执行");
        }
    };

    public class MyBinder extends Binder {
        /**
         * 获取当前Service的实例
         * @return
         */
        public MyService getService(){
            return MyService.this;
        }
    }

}
