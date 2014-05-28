package br.com.sasolucoes.yourmarket.business.company.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.sasolucoes.yourmarket.network.HttpGetter;
import br.com.sasolucoes.yourmarket.network.JsonUtils;
import br.com.sasolucoes.yourmarket.network.ServerUtils;
import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class CompanyRefresh extends IntentService {
	static final String TAG = "CompanyRefresh";
	static final String GET_COMPANY_ROUTE = "get_company";

	public CompanyRefresh() {
		super(TAG);
	}

	/* 
	 * Worker thread - where the job is done! 
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "Work in progress...");

		getDataFromServer();
		processData();
		
		Log.d(TAG, "Work done!");
	}

	private void getDataFromServer() {
		Log.d(TAG, "getDataFromServer() - start");
		
		
		Log.d(TAG, "getDataFromServer() - finish");
	}
	
	
	private URL getUrl() {
		return ServerUtils.createUrl(ServerUtils.formatedAddress() + GET_COMPANY_ROUTE);
	}
	
	
	

	private class GetConnectionTask extends AsyncTask<URL, Void, String> {

		@Override
		protected String doInBackground(URL... urls) {
			InputStream response = null;
			
			response = getServerResponseFromURL(response, urls[0]);
			
			if (response == null)
				return "";
			
			return convertInputStreamToString(response);
			
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (result.equals(TAG))
				return;

		}

		private InputStream getServerResponseFromURL(InputStream response, URL url) {
			HttpURLConnection connection = null;
			
			try {
				connection = (HttpURLConnection)url.openConnection();
				response = new BufferedInputStream(connection.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (connection != null)
					connection.disconnect();
			}
			return response;
		}

		private String convertInputStreamToString(InputStream response) {
			InputStreamReader in = new InputStreamReader(response);
			BufferedReader br = new BufferedReader(in); 
			
			String textline = null;
			StringBuilder builder = new StringBuilder(); 
			
			try {
				while((textline = br.readLine()) != null) {
					builder.append(textline);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return builder.toString();
		}
	}
	
	private void processData() {
		Log.d(TAG, "processData() - start");
		
		Log.d(TAG, "processData() - finish");
	}

}
