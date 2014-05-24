package br.com.sasolucoes.yourmarket.business.seller;

import android.app.Activity;
import android.os.Bundle;

public class SellerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (isActivityCreated(savedInstanceState))
			return;
		
		addSellerCompanyFragment();
		addSellerContactFragment();
	}

	private void addSellerCompanyFragment() {
		SellerCompanyFragment fragment = new SellerCompanyFragment(); 
		getFragmentManager()
			.beginTransaction()
			.add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
			.commit();
	}

	private void addSellerContactFragment() {
		SellerContactFragment fragment = new SellerContactFragment(); 
		getFragmentManager()
			.beginTransaction()
			.add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
			.commit();
	}

	private boolean isActivityCreated(Bundle savedInstanceState) {
		return (savedInstanceState != null);
	}

	
	
}
