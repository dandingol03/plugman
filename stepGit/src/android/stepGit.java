package com.outstudio.plugin.stepGit;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import android.content.Intent;
import android.app.Activity;
import android.widget.Toast;
import android.os.Build;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.outstudio.plugin.stepGit.util.FakeR;
import com.outstudio.plugin.stepGit.activity.StepGitActivity;


/**
 * This class echoes a string called from JavaScript.
 */
public class stepGit extends CordovaPlugin {

    private CallbackContext callbackContext;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext=callbackContext;
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);

        }
        else if(action.equals("serviceInvoke"))
        {
            String message=args.getString(0);
            Intent intent = new Intent(cordova.getActivity(), StepGitActivity.class);
            if (this.cordova != null) {
                this.cordova.startActivityForResult((CordovaPlugin) this, intent, 0);
            }
            //PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
            //r.setKeepCallback(true);
            //callbackContext.sendPluginResult(r);
        }else{}
        return true;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
        //TODO:add your process in coolMethod
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }


    private void serviceInvoke(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
        //TODO:add your process in serviceInvoke
            Intent intent = new Intent(cordova.getActivity(), StepGitActivity.class);

                Toast toast = Toast.makeText(
                          Build.VERSION.SDK_INT >= 21 ? cordova.getActivity().getWindow().getContext() : cordova.getActivity().getApplicationContext(),
                          "it is a toast",
                          Toast.LENGTH_LONG // actually controlled by a timer further down
                      );
                      toast.show();
            cordova.startActivityForResult((CordovaPlugin)this, intent, 200);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    /*回调接口*/
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == Activity.RESULT_OK ) {
                    this.callbackContext.success("dwdwdwdw");
                }else {
                    this.callbackContext.error("what the fuck");
                }
    }



}
