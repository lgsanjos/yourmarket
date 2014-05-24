package br.com.sasolucoes.yourmarket.business.seller;

import br.com.sasolucoes.yourmarket.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SellerCompanyFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.seller_company_fragment, container, false);
		return view;
	}

}
