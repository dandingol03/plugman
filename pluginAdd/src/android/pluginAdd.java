package com.plugin.pluginAdd;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.os.Build;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.Toast;


import android.content.Context;
import android.app.Notification;
import android.app.NotificationManager;



/**
 * This class echoes a string called from JavaScript.
 */
public class pluginAdd extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {

        FakeR fakeR = new FakeR((Context)cordova.getActivity());

         NotificationManager manager = (NotificationManager)cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification=new Notification.Builder((Context)cordova.getActivity())
                        		.setAutoCancel(true)
                        		.setContentTitle("这是通知组件的标题")
                        		.setContentText("这是通知组件的内容")
                        		.setTicker("fuck u")
                        		.setSmallIcon(fakeR.getId("drawable", "loading_icon"))
                        		.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
                        		.setWhen(System.currentTimeMillis())
                        		.setPriority(Notification.PRIORITY_MAX)
                        		.build();
                        manager.notify(1, notification);


            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
