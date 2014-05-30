package br.com.sasolucoes.yourmarket.business.company.orm;

import static br.com.sasolucoes.yourmarket.business.company.orm.CompanyContract.AUTHORITY;
import static br.com.sasolucoes.yourmarket.business.company.orm.CompanyContract.COMPANY_DIR;
import static br.com.sasolucoes.yourmarket.business.company.orm.CompanyContract.COMPANY_ITEM;
import static br.com.sasolucoes.yourmarket.business.company.orm.CompanyContract.TABLE;
import br.com.sasolucoes.yourmarket.orm.DbHelper;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class CompanyProvider extends ContentProvider {
	static final String TAG = "CompanyProvider";
	private DbHelper dbHelper;
	
	private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static {
		sURIMatcher.addURI(AUTHORITY, TABLE, COMPANY_DIR);
		sURIMatcher.addURI(AUTHORITY, TABLE + "/#", COMPANY_ITEM);		
	}
	
	@Override
	public boolean onCreate() {
		Log.d(TAG, "onCreate");
		dbHelper = new DbHelper(getContext());
		return false;
	}

	@Override
	public String getType(Uri uri) {
		switch(sURIMatcher.match(uri)) {
		case COMPANY_DIR:
			Log.d(TAG, String.format("getType: %d", COMPANY_DIR));
			return CompanyContract.COMPANY_TYPE_DIR;
		case COMPANY_ITEM:
			Log.d(TAG, String.format("getType: %d", COMPANY_ITEM));
			return CompanyContract.COMPANY_TYPE_ITEM;
		default:
			throw new IllegalArgumentException("Illegal uri: " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		qb.setTables(TABLE);
		
		switch(sURIMatcher.match(uri)) {
		case COMPANY_DIR:
			break;
		case COMPANY_ITEM:
			qb.appendWhere(CompanyContract.Column.ID + "=" + uri.getLastPathSegment());
			break;
		default:
			throw new IllegalArgumentException("Illegal uri" + uri);
		}
		
		String orderBy = (TextUtils.isEmpty(sortOrder)) ? CompanyContract.DEFAULT_SORT : sortOrder;
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		
		// register for uri changes
		// when a insert, update or delete notify changes, this cursor will know.
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		
		Log.d(TAG, "Queried records: " + cursor.getCount());
		
		return cursor;
	}


	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.d(TAG, "insert");
		
		Uri ret = null;
				
		// Assert correct uri. For a insert, it can't be a item type (COMPANY_TYPE_ITEM)
		if (sURIMatcher.match(uri) != COMPANY_DIR)
			throw new IllegalArgumentException("Illegal uri: " + uri);
		
		// Write on database and get the ID of the new record
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long rowId = db.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		
		// any error?
		if (rowId == -1)
			return ret;
		
		long id = values.getAsLong(CompanyContract.Column.ID);
		ret = ContentUris.withAppendedId(uri, id);
		
		Log.d(TAG, "inserted  uri: " + ret);
		
		return ret;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		Log.d(TAG, "delete");
		
		String where;
		switch(sURIMatcher.match(uri)) {
		case COMPANY_DIR:
			where = (selection == null) ? "" : selection;
			break;
		case COMPANY_ITEM:
			long id = ContentUris.parseId(uri);
			where = CompanyContract.Column.ID + " = " + id + (TextUtils.isEmpty(selection) ? "" : " and (" + selection + ")");
			break;
		default:
			throw new IllegalArgumentException("Illegal uri: " + uri);
		}
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int rowsAffected  = db.delete(TABLE, where, selectionArgs);
		
		// notify that data for this uri has changed
		if (rowsAffected>0)
			getContext().getContentResolver().notifyChange(uri, null);
		
		return rowsAffected;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		String where;
		
		switch(sURIMatcher.match(uri)) {
		case COMPANY_DIR:
			where = selection;
			break;
		case COMPANY_ITEM:
			long id = ContentUris.parseId(uri);
			where = CompanyContract.Column.ID + " = " + id + (TextUtils.isEmpty(selection) ? "" : " and (" + selection + ")");
			break;
		default:
			throw new IllegalArgumentException("Illegal uri: " + uri);
		}
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		int rowsAffected = db.update(TABLE, values, where, selectionArgs);
		
		if (rowsAffected>0)
			getContext().getContentResolver().notifyChange(uri, null);
		
		return rowsAffected;
	}

}
