package br.com.sasolucoes.yourmarket.business.company.orm;

import static br.com.sasolucoes.yourmarket.business.company.orm.CompanyContract.CONTENT_URI;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class CompanyRepository {
	static final String TAG = "CompanyRepository";

	public static int insert(Context context, Company company) {
		Log.d(TAG, "insert");
		
		int result = 0;
		
		if (context == null || company == null)
			return result;
		
		ContentValues values = new ContentValues();
		
		values.clear();
		values.put(CompanyContract.Column.ID, company.getId());
		values.put(CompanyContract.Column.NAME, company.getName());
		values.put(CompanyContract.Column.PHONE, company.getPhone());
		values.put(CompanyContract.Column.CELLPHONE, company.getCellphone());
		values.put(CompanyContract.Column.EMAIL, company.getEmail());
		values.put(CompanyContract.Column.LOGO, company.getLogo());
		
		Uri uri = context.getContentResolver().insert(CompanyContract.CONTENT_URI, values);
		
		if (uri != null)
			result++;
		
		return result;
	}
	
	public static int delete(Context context, long id) {
		Log.d(TAG, "delete");
		
		if (context == null)
			return 0;

		Uri uri = Uri.parse(CONTENT_URI + "/" + id);
		return context.getContentResolver().delete(uri, null, null);
	}
	
	public static int deleteAll(Context context) {
		Log.d(TAG, "deleteAll");
		
		if (context == null)
			return 0;

		return context.getContentResolver().delete(CONTENT_URI, null, null);
	}
	
	public static Company selectFirst(Context context) {
		Log.d(TAG, "selectFirst");
		
		Cursor cursor = context.getContentResolver().query(CompanyContract.CONTENT_URI, null, null, null, null);
	
		Company company = getCompanyFromCursor(cursor);
		
		return company;
	}

	private static Company getCompanyFromCursor(Cursor cursor) {
		Log.d(TAG, "getCompanyFromCursor");
		
		Company company = new Company();
		
		if (cursor == null)
			return company;
		
		if (!cursor.moveToFirst())
			return company;
		
		company.setId(cursor.getInt(cursor.getColumnIndex(CompanyContract.Column.ID)));
		company.setName(cursor.getString(cursor.getColumnIndex(CompanyContract.Column.NAME)));
		company.setPhone(cursor.getString(cursor.getColumnIndex(CompanyContract.Column.PHONE)));
		company.setCellphone(cursor.getString(cursor.getColumnIndex(CompanyContract.Column.CELLPHONE)));
		company.setEmail(cursor.getString(cursor.getColumnIndex(CompanyContract.Column.EMAIL)));
		
		return company;
	}
	
}
