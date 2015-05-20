package org.pocketworkstation.pckeyboard;

import android.content.ContentValues;
import android.util.Log;

public class PrefDatabase {	
	public static final String TABLE_NAME = "preferences";
	public static final String COL_PREF_NAME = "prefName";
	public static final String COL_ENABLED = "enabled";
	static final String TAG = "PCKeyboard/PrefDatabase";
	
	public static final String[] FIELDS = { COL_PREF_NAME, COL_ENABLED };
	
	static final String CREATE_DB_TABLE = "CREATE TABLE " 
		      + TABLE_NAME
		      + "(" 
		      + COL_PREF_NAME + " TEXT NOT NULL DEFAULT ''," 
		      + COL_ENABLED + " TEXT NOT NULL DEFAULT 'false'"  
		      + ");";	
	
	public static class Item {
		public String prefName = "";
		public String enabled = "false";
		
		public Item(){ 
			Log.i(TAG, "PrefDatabase.Item() called");
		}
		
		public ContentValues getContent() {
			Log.i(TAG, "PrefDatabase.getContent() called");
			
			final ContentValues values = new ContentValues();
			values.put(COL_PREF_NAME, prefName);		
			values.put(COL_ENABLED, enabled);
			return values;
		}
	}
}