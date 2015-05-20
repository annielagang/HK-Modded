package org.pocketworkstation.pckeyboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PrefDbHelper extends SQLiteOpenHelper {
	
	private static PrefDbHelper singleton;
	static final String TAG = "PCKeyboard/PrefDbHelper";
	
	public static PrefDbHelper getInstance(final Context context) {
		if (singleton == null) {
			singleton = new PrefDbHelper(context);
		}
		return singleton;
	}
	
	public static final String DATABASE_NAME = "HKModdedDb";
	public static final int DATABASE_VERSION = 1;
	
	private final Context context;
	
	public PrefDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context.getApplicationContext();
		Log.i(TAG, "PrefDbHelper.PrefDbHelper() called");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(PrefDatabase.CREATE_DB_TABLE);
		Log.i(TAG, "PrefDbHelper.onCreate() called");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + PrefDatabase.TABLE_NAME);
		onCreate(db);
		Log.i(TAG, "PrefDbHelper.onUpgrade() called");
	}
	
	public synchronized boolean putItem(final PrefDatabase.Item item) {
		boolean success = false;
		int result = 0;
		final SQLiteDatabase db = this.getWritableDatabase();
		
		Log.i(TAG, "PrefDbHelper.putItem() called");
		
		result = db.update(PrefDatabase.TABLE_NAME, item.getContent(),
				PrefDatabase.COL_PREF_NAME + " = ?",
				new String[] { String.valueOf(item.prefName) });
		
		Log.i(TAG, "PrefDbHelper.UpdateResult (rows affected): " +  String.valueOf(result));

		if (result > 0) {
			success = true;
		} else {
			final long id = db.insert(PrefDatabase.TABLE_NAME, null, item.getContent());

			if (id > -1) {
				success = true;
			}
			
			Log.i(TAG, "PrefDbHelper.Insert (rows affected): " +  String.valueOf(id));
		}
		
		if (success) {
			notifyProviderOnChange();
		}
		
		return success;
	}
	
	private void notifyProviderOnChange() {
		context.getContentResolver().notifyChange(PrefProvider.CONTENT_URI, null, false);
		Log.i(TAG, "PrefDbHelper.notifyProviderOnChange() called");
	}
}