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

		String companyData = getCompanyDataFromServer();
		processData(companyData);
		
		Log.d(TAG, "Work done!");
	}

	private void processData(String data) {
		Log.d(TAG, "processData()");
	}
	
	
	private String getCompanyDataFromServer() {
		Log.d(TAG, "getDataFromServer()");
		
		GetFromURLConnectionTask serverConnection = new GetFromURLConnectionTask();
		serverConnection.execute(getUrl());
		return serverConnection.getResponse(); 
	}
	
	private URL getUrl() {
		return ServerUtils.createUrl(ServerUtils.formatedAddress() + GET_COMPANY_ROUTE);
	}
	
	private class GetFromURLConnectionTask extends AsyncTask<URL, Void, Void> {
		private StringBuilder response = new StringBuilder();
		
		public String getResponse() {
			return response.toString();
		}

		/* 
		 * Thread process - where the job is done
		 */
		@Override
		protected Void doInBackground(URL... urls) {
			InputStream stream = null;
			
			stream = getServerResponseFromURL(stream, urls[0]);
			setResponse(stream);
			
			return null;
		}
	
		private InputStream getServerResponseFromURL(InputStream response, URL url) {
			HttpURLConnection connection = null;
			
			try {
				connection = (HttpURLConnection)url.openConnection();
				response = new BufferedInputStream(connection.getInputStream());
			} catch (IOException e) {
				Log.d(TAG, e.toString());
				e.printStackTrace();
			} finally {
				if (connection != null)
					connection.disconnect();
			}
			return response;
		}

		private void setResponse(InputStream stream) {
			response = new StringBuilder();
			if (stream == null)
				return;
			
			InputStreamReader in = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(in); 
			String textline = null;
			try {
				while((textline = br.readLine()) != null) {
					response.append(textline);
				}
			} catch (IOException e) {
				Log.d(TAG, e.toString());
				e.printStackTrace();
			}
		}

	}
	
}
