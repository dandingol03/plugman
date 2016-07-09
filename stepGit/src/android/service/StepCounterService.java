package com.outstudio.plugin.stepGit.service;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;


import org.json.JSONException;
import org.json.JSONObject;
import com.outstudio.plugin.stepGit.util.FakeR;
import com.outstudio.plugin.stepGit.util.StepDetector;

public class StepCounterService extends Service {

    public static Boolean FLAG = false;// 服务运行标志
	private SensorManager mSensorManager;// 传感器服务
	private StepDetector detector;// 传感器监听对象
	private PowerManager mPowerManager;// 电源管理服务
	private WakeLock mWakeLock;// 屏幕灯

    @Override
    public void onCreate() {
        super.onCreate();
        FLAG = true;// 标记为服务正在运行

        // 创建监听器类，实例化监听对象
        detector = new StepDetector(this);

        // 获取传感器的服务，初始化传感器
        mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        // 注册传感器，注册监听器
        mSensorManager.registerListener(detector,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);

        // 电源管理服务
        mPowerManager = (PowerManager) this
                .getSystemService(Context.POWER_SERVICE);
        mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
                | PowerManager.ACQUIRE_CAUSES_WAKEUP, "S");
        mWakeLock.acquire();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		FLAG = false;// 服务停止
		if (detector != null) {
			mSensorManager.unregisterListener(detector);
		}

		if (mWakeLock != null) {
			mWakeLock.release();
		}
	}

}