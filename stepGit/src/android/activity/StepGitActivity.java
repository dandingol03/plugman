package com.outstudio.plugin.stepGit.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import org.json.JSONException;
import org.json.JSONObject;
import com.outstudio.plugin.stepGit.util.FakeR;

public class StepGitActivity extends Activity {

    private FakeR fakeR;
    private Button btn$start$service;
    private Button callback$button;



        private void init(){
            btn$start$service=(Button)findViewById(fakeR.getId("id","btn$start$service"));
            btn$start$service.setOnClickListener(new OnClickListener(){
                @Override
               public void onClick(View v) {
                  Intent intent = new Intent(StepGitActivity.this, CountingActivity.class);
                  intent.putExtra("isRun", false);
                  startActivityForResult(intent, 1);
               }
            });
            callback$button=(Button)findViewById(fakeR.getId("id", "callback$button"));
            callback$button.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   setResult(Activity.RESULT_OK);
                   finish();
               }
            });
        }

        @Override
        public void onCreate (Bundle state) {
            super.onCreate(state);
            fakeR = new FakeR(this);
            setContentView(fakeR.getId("layout", "stepgit_layout"));
            init();
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
            Toast toast = Toast.makeText(
                       StepGitActivity.this,
                      "counting activity is over\n="+result,
                      Toast.LENGTH_LONG // actually controlled by a timer further down
                  );
                  toast.show();
        }


        @Override
        public void onBackPressed() {
        // 这里处理逻辑代码，大家注意：该方法仅适用于2.0或更新版的sdk
             setResult(Activity.RESULT_OK);
             finish();

        }


}