package com.outstudio.plugin.jsNotification;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.outstudio.plugin.jsNotification.util.FakeR;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Environment;



/**
 * This class echoes a string called from JavaScript.
 */
public class jsNotification extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }
        else if(action.equals("store"))
        {
        	String key=args.getString(0);
        	String val=args.getString(1);
        	Properties prop = new Properties();
        	if(key!=null&&val!=null&&!key.equals(""))
        	{
        		prop.put(key, val);  
            	String path=Environment.getExternalStorageDirectory().getPath(); 
            	saveConfig( path+"/Document/ionic-react/config.dat", prop);  	
        	}
        }else if(action.equals("fetch"))
        {
        	String path=Environment.getExternalStorageDirectory().getPath();
        	Properties 	properties=loadConfig(path+"/Document/ionic-react/config.dat");
        	String key=args.getString(0);
        	if(key!=null&&!key.equals(""))
        	{
        		String value=properties.getProperty(key);
            	if(value!=null&&!value.equals(""))
            		callbackContext.success(value);	
        	}
    		callbackContext.error("key is invalid");
        }
        else if(action.equals("download"))
        {
        	String remote=args.getString(0);
        	String filePath=args.getString(1);
        	try{
        		InputStream is=null;
        		
        		HttpURLConnection conn=null;
        		 
	        	URL url=new URL(remote+"?"+"path="+filePath);
	        	conn = (HttpURLConnection) url.openConnection();  
	        	 conn.setDoInput(true);  
	             conn.setDoOutput(true);  
	         
	            	  is = conn.getInputStream();  
	            	  
		                
	 	        	 String root=Environment.getExternalStorageDirectory().getPath();
	 	        	 //make the unexisted dir exists
	 	        	 File dir=new File(root+"/Document/ionic-react/"+filePath.substring(0, filePath.lastIndexOf("/")));
	 	        	 if(!dir.exists())
	 	        		 dir.mkdirs();
	 	        	 //make the unexisted file exists
	 	        	 File f=new File(root+"/Document/ionic-react/"+filePath);
	 	        	 if(!f.exists())
	 	        		 f.createNewFile();
	 	        	 FileOutputStream fos = new FileOutputStream(root+"/Document/ionic-react/"+filePath);  
	 	        	 byte[] buff=new byte[1024];
	 	        	 int hasRead=0;
	         	    while ((hasRead = is.read(buff)) > 0) {  
	                     fos.write(buff, 0, hasRead);  
	                 }  
	         	    is.close();
	         	    fos.close();
	         	    callbackContext.success(root+"/Document/ionic-react/"+filePath);
	             
	             
        	}catch(Exception e)
        	{
        		e.printStackTrace();
        		callbackContext.error(e.getMessage());
        	}
        	
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
    
    
    private Properties loadConfig(String file) {  
    	Properties properties = new Properties();  
    	try {  
	    	FileInputStream s = new FileInputStream(file);  
	    	properties.load(s);  
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	}  
    	return properties;  
    }  
    
    private void saveConfig( String file, Properties properties) {  
    	try {  
	    	FileOutputStream s = new FileOutputStream(file, false);  
	    	properties.store(s, "");  
    	} catch (Exception e){  
    		e.printStackTrace();  
    	}  
    }  
    
    
}
