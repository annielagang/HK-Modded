package org.pocketworkstation.pckeyboard;

import android.util.Log;
import android.view.KeyEvent;
import android.view.KeyEvent.Callback;

// SOURCE: https://android.googlesource.com/platform/cts/+/1f2fa1be3e1b1eb7d97fdc262ee530ecf9eec401/tests/tests/view/src/android/view/cts/KeyEventTest.java
public class MockCallback implements Callback {
    private boolean mIsKeyDown;
    private boolean mIsKeyUp;
    private boolean mIsMultiple;
    private int mKeyCode;
    private KeyEvent mKeyEvent;
    private int mCount;
    private String mDescription;
    
    public MockCallback(String description) {
    	mDescription = description;
    }
    
    public boolean isKeyDown() {
    	Log.i("MockCallback", "MockCallback.isKeyDown() - " + mDescription + ":" + mIsKeyDown);
        return mIsKeyDown;
    }
    
    public boolean isKeyUp() {
    	Log.i("MockCallback", "MockCallback.isKeyUp() - " + mDescription + ":" + mIsKeyUp);
        return mIsKeyUp;
    }
    
    public boolean isKeyMultiple() {
    	Log.i("MockCallback", "MockCallback.isKeyMultiple() - " + mDescription + ":" + mIsMultiple);
        return mIsMultiple;
    }
    
    public int getKeyCode() {
    	Log.i("MockCallback", "MockCallback.getKeyCode() - " + mDescription + ":" + mKeyCode);
        return mKeyCode;
    }
    
    public KeyEvent getKeyEvent() {
    	Log.i("MockCallback", "MockCallback.getKeyEvent() - " + mDescription + ":" + mKeyEvent);
        return mKeyEvent;
    }
    
    public int getCount() {
    	Log.i("MockCallback", "MockCallback.getCount() - " + mDescription + ":" + mCount);
        return mCount;
    }
    
    public void reset() {
        mIsKeyDown = false;
        mIsKeyUp = false;
        mIsMultiple = false;
        
        Log.i("MockCallback", "MockCallback.reset() - " + mDescription);
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mIsKeyDown = true;
        mKeyCode = keyCode;
        mKeyEvent = event;        
        Log.i("MockCallback", "MockCallback.onKeyDown() - " + mDescription + ":" + mIsKeyDown);        
        return true;
    }
    
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
    	Log.i("MockCallback", "MockCallback.onKeyLongPress() - " + mDescription + ":" + false);   
        return false;
    }
    
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        mIsMultiple = true;
        mKeyCode = keyCode;
        mKeyEvent = event;
        mCount = count;
        if (count < 1) {
        	Log.i("MockCallback", "MockCallback.onKeyMultiple() - " + mDescription + ":" + false); 
            //return false; // this key event never repeat.
        	return true;
        }
        Log.i("MockCallback", "MockCallback.onKeyMultiple() - " + mDescription + ":" + true); 
        return true;
    }
    
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mIsKeyUp = true;
        mKeyCode = keyCode;
        mKeyEvent = event;
        Log.i("MockCallback", "MockCallback.onKeyMultiple() - " + mDescription + ":" + mIsKeyUp); 
        return true;
    }
}
