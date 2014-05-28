package br.com.sasolucoes.yourmarket.business.company.orm;

import android.provider.BaseColumns;

public class CompanyContract {
	static final String TAG = "CompanyContract";
	
	public static final String TABLE = "company";
	
	public class Column {
		public static final String ID = BaseColumns._ID;
		public static final String NAME = "name";
		public static final String PHONE = "phone";
		public static final String CELLPHONE = "cellphone";
		public static final String EMAIL = "email";
		public static final String LOGO = "logo";
	}

}
