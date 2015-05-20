package org.pocketworkstation.pckeyboard;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class PrefProvider extends ContentProvider {	
	SQLiteDatabase db;
	
	static final String AUTHORITY = "org.pocketworkstation.pckeyboard";
	public static final String SCHEME = "content://";
	static final String URL = SCHEME + AUTHORITY + "/prefs";
	static final Uri CONTENT_URI = Uri.parse(URL);
	
	private static HashMap<String, String> selectColsFrDb;
	static final int uriCode = 1;
	
	static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		uriMatcher.addURI(AUTHORITY, "prefs", uriCode);
		uriMatcher.addURI(AUTHORITY, "prefs/*", uriCode);
		
		selectColsFrDb = new HashMap<String, String>();
		selectColsFrDb.put(PrefDatabase.COL_PREF_NAME, PrefDatabase.COL_PREF_NAME);
		selectColsFrDb.put(PrefDatabase.COL_ENABLED, PrefDatabase.COL_ENABLED);
	}
	
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/prefs";
	static final String TAG = "PCKeyboard/PrefsProvider";
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) { return 0; }

	@Override
	public String getType(Uri uri) {
		Log.i(TAG, "PrefsProvider.getType() called");
		switch (uriMatcher.match(uri)) {
		case uriCode:
			return CONTENT_TYPE;

		default:
			throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) { return null; }

	@Override
	public boolean onCreate() {
		Log.i(TAG, "PrefsProvider.onCreate() called");
		Context context = getContext();
		PrefDbHelper dbHelper = new PrefDbHelper(context);
		db = dbHelper.getReadableDatabase();
		if (db != null) {
			return true;
		}
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.i(TAG, "PrefsProvider.query() called");
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		
		qb.setTables(PrefDatabase.TABLE_NAME);
		
		switch (uriMatcher.match(uri)) {
			case uriCode:
				qb.setProjectionMap(selectColsFrDb);
				break;
			default:
				throw new IllegalArgumentException("Unsupported URI " + uri);
		}
		
		if (sortOrder == null || sortOrder == "") {
			sortOrder = PrefDatabase.COL_PREF_NAME;
		}		
		
		Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) { return 0; }
}
