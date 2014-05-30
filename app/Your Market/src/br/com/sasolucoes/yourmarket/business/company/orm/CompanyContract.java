package br.com.sasolucoes.yourmarket.business.company.orm;

import android.net.Uri;
import android.provider.BaseColumns;

public class CompanyContract {
	static final String TAG = "CompanyContract";
	
	public static final String TABLE = "company";
	public static final String AUTHORITY = "br.com.sasolucoes.yourmarket.business.company.orm.CompanyProvider";
	public static final String DIR = "br.com.sasolucoes.yourmarket.business.company.orm.CompanyProvider";
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE);

	public static final int COMPANY_DIR = 1;
	public static final int COMPANY_ITEM = 2;

	public static final String COMPANY_TYPE_DIR = "vnd.android.cursor.dir/vnd.sasolucoes.yourmarket.provider.company";
	public static final String COMPANY_TYPE_ITEM = "vnd.android.cursor.item/vnd.sasolucoes.yourmarket.provider.company";

	public static final String DEFAULT_SORT = "";
	
	public class Column {
		public static final String ID = BaseColumns._ID;
		public static final String NAME = "name";
		public static final String PHONE = "phone";
		public static final String CELLPHONE = "cellphone";
		public static final String EMAIL = "email";
		public static final String LOGO = "logo";
	}

}
