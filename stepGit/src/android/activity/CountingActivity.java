package com.outstudio.plugin.stepGit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Handler;
import android.os.Message;



import com.outstudio.plugin.stepGit.util.FakeR;
import com.outstudio.plugin.stepGit.util.StepDetector;
import com.outstudio.plugin.stepGit.service.StepCounterService;


public class CountingActivity extends Activity {

    private FakeR fakeR;
    private Thread thread;  //定义线程对象
    private Button callback$button;
	private Button btn_start;// 开始按钮
	private Button btn_stop;// 停止按钮
    private TextView tv_show_step;// 步数

    //int variable
    private int total_step = 0;   //走的总步数
    private  long startTimer = 0;// 开始时间
    private  long tempTime = 0;
    private long timer = 0;// 运动时间
    private boolean isRun = false;

    Handler handler = new Handler() {

    		@Override
    		public void handleMessage(Message msg) {
    			// TODO Auto-generated method stub
    			super.handleMessage(msg);        // 此处可以更新UI
    			countStep();          //调用步数方法
    			tv_show_step.setText(total_step + "");// 显示当前步数

    		}
    	};



        private void init(){
            btn_start=(Button)findViewById(fakeR.getId("id","start"));
            btn_stop=(Button)findViewById(fakeR.getId("id","stop"));
            tv_show_step=(TextView)findViewById(fakeR.getId("id","show_step"));

            callback$button=(Button)findViewById(fakeR.getId("id", "cancel"));
            callback$button.setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent=new Intent();
                   intent.putExtra("result","cancle is clicked,and data is send successfully");
                   setResult(Activity.RESULT_OK,intent);
                   finish();
               }
            });
            Intent service = new Intent(this, StepCounterService.class);
            stopService(service);
            StepDetector.CURRENT_SETP = 0;
            tempTime = timer = 0;
            tv_show_step.setText("0");
            //清除handle的遗留消息队列
            handler.removeCallbacks(thread);
            tv_show_step.setText(total_step + "");
            btn_start.setEnabled(!StepCounterService.FLAG);
            btn_stop.setEnabled(StepCounterService.FLAG);
            if (StepCounterService.FLAG) {
                btn_stop.setText("暂停");
            } else if (StepDetector.CURRENT_SETP > 0) {
                btn_stop.setEnabled(true);
                btn_stop.setText("清零");
            }
            btn_start.setOnClickListener(new OnClickListener() {
                 @Override
                 public void onClick(View v) {
                	Intent service = new Intent(CountingActivity.this, StepCounterService.class);
                    startService(service);
                    btn_start.setEnabled(false);
                    btn_stop.setEnabled(true);
                    btn_stop.setText("暂停");
                    startTimer = System.currentTimeMillis();
                    tempTime = timer;
                 }
             });

            btn_stop.setOnClickListener(new OnClickListener() {
                 @Override
                 public void onClick(View v) {
                	Intent service = new Intent(CountingActivity.this, StepCounterService.class);
                    stopService(service);
                    if (StepCounterService.FLAG && StepDetector.CURRENT_SETP > 0) {
                        btn_stop.setText("清零");
                    } else {
                        StepDetector.CURRENT_SETP = 0;
                        tempTime = timer = 0;

                        btn_stop.setText("暂停");
                        btn_stop.setEnabled(false);
                        tv_show_step.setText("0");
                        handler.removeCallbacks(thread);
                    }
                    btn_start.setEnabled(true);
                 }
             });



        }

        private void init_thread()
        {
            if (thread == null)
            {
                thread = new Thread()
                {

                    @Override
                    public void run()
                    {
                        // TODO Auto-generated method stub
                        super.run();
                        int temp = 0;
                        while (true) {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            if (StepCounterService.FLAG) {
                                Message msg = new Message();
                                if (temp != StepDetector.CURRENT_SETP) {
                                    temp = StepDetector.CURRENT_SETP;
                                }
                                if (startTimer != System.currentTimeMillis()) {
                                    timer = tempTime + System.currentTimeMillis()
                                            - startTimer;
                                }
                                handler.sendMessage(msg);// 通知主线程
                            }
                        }
                    }
                };
                thread.start();
            }
        }

        /**
         * 实际的步数
         */
        private void countStep() {
            if (StepDetector.CURRENT_SETP % 2 == 0) {
                total_step = StepDetector.CURRENT_SETP+1;
            } else {
                total_step = StepDetector.CURRENT_SETP +1;
            }
            
        }

        @Override
        public void onCreate (Bundle state) {
            super.onCreate(state);
            fakeR = new FakeR(this);
            setContentView(fakeR.getId("layout", "stepgit_count"));
            init_thread();
            //取出启动当前activity的参数
            Bundle extras = getIntent().getExtras();
            isRun = extras.getBoolean("run");
        }

        @Override
        protected void onResume() {
            // TODO Auto-generated method stub
            super.onResume();
            // 获取界面控件
            init();


        }



        @Override
        public void onBackPressed() {
        // 这里处理逻辑代码，大家注意：该方法仅适用于2.0或更新版的sdk
             setResult(Activity.RESULT_OK);
             finish();

        }


        @Override
        protected void onDestroy() {
            // TODO Auto-generated method stub
            super.onDestroy();
        }



}