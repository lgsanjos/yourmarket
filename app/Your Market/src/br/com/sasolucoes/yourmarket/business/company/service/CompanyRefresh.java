package br.com.sasolucoes.yourmarket.business.company.service;

import static br.com.sasolucoes.yourmarket.network.ServerUtils.CONNECT_TIMEOUT;
import static br.com.sasolucoes.yourmarket.network.ServerUtils.READ_TIMEOUT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import br.com.sasolucoes.yourmarket.business.category.subcategory.Subcategory;
import br.com.sasolucoes.yourmarket.business.company.orm.Company;
import br.com.sasolucoes.yourmarket.network.ServerUtils;

public class CompanyRefresh extends IntentService {
	static final String TAG = "CompanyRefresh";
	static final String COMPANY_MODULE_ROUTE = "company/";
	static final String GET_COMPANY_ROUTE = "get_company/";

	public CompanyRefresh() {
		super(TAG);
	}

	/* 
	 * Worker thread - where the service is executed. 
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent");

		if (!isNetworkConnected())
			Log.d(TAG, "Network unavaiable.");
		
		new GetFromURLConnectionTask().execute(getUrl());
	}

	private boolean isNetworkConnected() {
		Log.d(TAG, "isNetworkConnected");
		
		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		
		return (networkInfo != null && networkInfo.isConnected());
	}

	private URL getUrl() {
		Log.d(TAG, "getUrl");
		
		return ServerUtils.createUrl(ServerUtils.formatedAddress() + COMPANY_MODULE_ROUTE + GET_COMPANY_ROUTE);
	}

	private class GetFromURLConnectionTask extends AsyncTask<URL, Void, String> {
		/* 
		 * Thread process - where the network task is done.
		 */
		@Override
		protected String doInBackground(URL... urls) {
			Log.d(TAG, "doInBackground");
			
			try {
				InputStream in = getResponseFromURL(urls[0]);
				String convertedStream = convertInputStreamToString(in);
				Log.d(TAG, "Http GET response is: " + convertedStream);
				
				return convertedStream;
				
			} catch (IOException e) {
				Log.d(TAG, e.toString());
				return "";
			}
		}
		
		/* 
		 * Post thread process - where the database is updated
		 */
		@Override
		protected void onPostExecute(String result) {
			Log.d(TAG, "onPostExecute");
			
			if (result.equals(""))
				return;
			
			

			Company company = convertFromJsonToCompany(result);
			
			// TODO: update database
		}

		private Company convertFromJsonToCompany(String result) {
			Type type = new TypeToken<ArrayList<Company>>(){}.getType();
			Gson gson = new Gson();
			return gson.fromJson(result, type);
		}


		private InputStream getResponseFromURL(URL url) throws IOException {
			Log.d(TAG, "getResponseFromURL");
			
			HttpURLConnection connection = null;
			connection = (HttpURLConnection)url.openConnection();
			connection.setReadTimeout(READ_TIMEOUT);
			connection.setConnectTimeout(CONNECT_TIMEOUT);
			connection.setRequestMethod(ServerUtils.REQUEST_METHOD);
			connection.setDoInput(true);
			connection.connect();
			
			int response = connection.getResponseCode();
			Log.d(TAG, "Http GET code response is: " + response);
			
			return connection.getInputStream();
		}
		
		private String convertInputStreamToString(InputStream stream) {
			Log.d(TAG, "convertInputStreamToString");
			
			if (stream == null)
				return "";
			
			StringBuilder response = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(stream)); 
			
			try {
				String textline = null;
				while((textline = br.readLine()) != null) {
					response.append(textline);
				}
			} catch (IOException e) {
				Log.d(TAG, e.toString());
			}
			
			return response.toString();
		}
	}
	
	
	
}
