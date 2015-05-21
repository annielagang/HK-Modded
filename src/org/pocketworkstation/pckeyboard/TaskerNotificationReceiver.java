package org.pocketworkstation.pckeyboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;

public class TaskerNotificationReceiver extends BroadcastReceiver {
	static final String TAG = "PCKeyboard/TaskerNotification";
    private LatinIME mIME;
    static final String ACTION = "org.pocketworkstation.pckeyboard.action.ACCESS_KEYS";
    static final String PLUGIN_KEY = "com.mypowerapps.android.tasker.norootdpad.extra.KEYCODES";
    static final String BUNDLE_KEY = "com.mypowerapps.android.tasker.norootdpad.extra.BUNDLE";
    
	TaskerNotificationReceiver(LatinIME ime) {
	 	super();
    	mIME = ime;
		Log.i(TAG, "TaskerNotificationReceiver created, ime=" + mIME);
	}
	
    @Override
    public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "TaskerNotificationReceiver.onReceive() called");
		
		if (ACTION.equals(intent.getAction())) {
			final Bundle resultBundle = intent.getBundleExtra(BUNDLE_KEY);
	        
	        if (resultBundle != null) {
	        	String keyCodeStr = resultBundle.getString(PLUGIN_KEY);
	        	
	        	if(keyCodeStr != null) {
	        		Log.i(TAG, "TaskerNotificationReceiver.keycodes.String: " + keyCodeStr);
	        		
	        		int keyCode = 0;
        			int repeat = 0;

        			try {
        				String[] values = keyCodeStr.split("-");
    				    keyCode = Integer.parseInt(values[0]);
    				    repeat = Integer.parseInt(values[1]);
        				    
    				    for (int i = 0; i < repeat; i++) {
    				    	if(keyCode == KeyEvent.KEYCODE_HOME) {
    				    		Intent homeIntent = new Intent(Intent.ACTION_MAIN);
    				    		homeIntent.addCategory(Intent.CATEGORY_HOME);
    				    		homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				    		context.startActivity(homeIntent);
    				    	} else {
    				    		keyDownUp(keyCode); 
    				    	}
    			        }
        			} catch(NumberFormatException e) {
        				Log.i(TAG, "TaskerNotificationReceiver - NumberFormatException");
        			}	        			
	        	} else {
	        		Log.i(TAG, "TaskerNotificationReceiver.keycodes NULL");
	        	}
	        } else {
	        	Log.i(TAG, "TaskerNotificationReceiver.resultBundle NULL");
	        }	        
		}
	}
    
    private void keyDownUp(int keyEventCode) {
    	InputConnection ic = mIME.getCurrentInputConnection();
    	long downTime = SystemClock.uptimeMillis();
        long eventTime = SystemClock.uptimeMillis();
        int repeat = 0; // key repeat 0 in Android means press the key once
        
        String description = String.valueOf(keyEventCode) + "(ACTION_DOWN)";
    	MockCallback callback = new MockCallback(description);
    	KeyEvent dpadEvent = new KeyEvent(downTime, eventTime, KeyEvent.ACTION_DOWN, keyEventCode, repeat);
    	callback.reset();   
    	callback.isKeyDown();
    	ic.sendKeyEvent(dpadEvent); 
    	
    	dpadEvent.dispatch(callback, null, null);
        callback.isKeyDown();
        callback.getKeyCode();
        callback.getKeyEvent();
    	
        description = String.valueOf(keyEventCode) + "(ACTION_UP)";
    	callback = new MockCallback(description);
    	dpadEvent = new KeyEvent(downTime, eventTime, KeyEvent.ACTION_UP, keyEventCode, repeat);
    	callback.reset();   
    	callback.isKeyUp();
    	ic.sendKeyEvent(dpadEvent);
    	
    	dpadEvent.dispatch(callback, null, null);
        callback.isKeyUp();
        callback.getKeyCode();
        callback.getKeyEvent();
    }
}
