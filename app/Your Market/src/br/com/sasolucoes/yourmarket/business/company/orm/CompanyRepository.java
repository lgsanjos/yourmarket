package br.com.sasolucoes.yourmarket.business.company.orm;

import static br.com.sasolucoes.yourmarket.business.company.orm.CompanyContract.CONTENT_URI;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;

public class CompanyRepository {

	public static int insert(Context context, Company company) {
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
		if (context == null)
			return 0;

		Uri uri = Uri.parse(CONTENT_URI + "/" + id);
		return context.getContentResolver().delete(uri, null, null);
	}
	
	public static int deleteAll(Context context) {
		if (context == null)
			return 0;

		return context.getContentResolver().delete(CONTENT_URI, null, null);
	}
	
}
