package br.com.sasolucoes.yourmarket.business.company.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.sasolucoes.yourmarket.R;
import br.com.sasolucoes.yourmarket.business.company.orm.Company;
import br.com.sasolucoes.yourmarket.business.company.orm.CompanyRepository;

public class SellerCompanyFragment extends Fragment {
	static String TAG = "SellerCompanyFragment"; 
	
	private TextView textName;
	private TextView textPhone;
	private TextView textCellphone;
	private TextView textEmail;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		
		View view = inflater.inflate(R.layout.seller_company_fragment, container, false);
		
		textName = (TextView)view.findViewById(R.id.text_name);
		textPhone = (TextView)view.findViewById(R.id.text_phone);
		textCellphone = (TextView)view.findViewById(R.id.text_cellphone);
		textEmail = (TextView)view.findViewById(R.id.text_email);
		
		return view;
	}
	
	@Override
	public void onResume() {
		Log.d(TAG, "onResume");
		
		super.onResume();
		updateView();
	}

	private void updateView() {
		Log.d(TAG, "updateView");
		
		textName.setText("");
		textPhone.setText("");
		textCellphone.setText("");
		textEmail.setText("");

		Company company = CompanyRepository.selectFirst(this.getActivity());
		
		if (company == null)
			return;
		
		textName.setText(company.getName());
		textPhone.setText(company.getPhone());
		textCellphone.setText(company.getCellphone());
		textEmail.setText(company.getEmail());
	}
}
