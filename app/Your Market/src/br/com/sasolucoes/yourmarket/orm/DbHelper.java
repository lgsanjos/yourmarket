package br.com.sasolucoes.yourmarket.orm;

import br.com.sasolucoes.yourmarket.business.company.orm.CompanyContract;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
	static String TAG = "DbHelper";
	static String DB_NAME = "yourmarket.db";
	static int DB_VERSION = 1;	
	
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		Log.d(TAG, String.format("DbHelper constructor"));
	}
	
	/* 
	 * Executed only once, when the db version is zero
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, String.format("onCreate()"));
		
		String sql = String
				.format("create table %s (%s int primary key, %s text, %s text, %s text, %s text, %s text)", 
						CompanyContract.TABLE,
						CompanyContract.Column.ID,
						CompanyContract.Column.NAME,
						CompanyContract.Column.PHONE,
						CompanyContract.Column.CELLPHONE,
						CompanyContract.Column.EMAIL,
						CompanyContract.Column.LOGO);

		Log.d(TAG, "onCreate with SQL:" + sql);
		db.execSQL(sql);
	}

	
	/* 
	 * Executed when the db version changes.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, String.format("onUpgrade(%d, %d", oldVersion, newVersion));

	}


}
