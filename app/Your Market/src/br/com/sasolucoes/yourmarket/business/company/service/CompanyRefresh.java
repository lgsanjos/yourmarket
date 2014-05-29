package br.com.sasolucoes.yourmarket.business.company.service;

import static br.com.sasolucoes.yourmarket.network.ServerUtils.CONNECT_TIMEOUT;
import static br.com.sasolucoes.yourmarket.network.ServerUtils.GET_RESPONSE_FROM_URL_FAIL;
import static br.com.sasolucoes.yourmarket.network.ServerUtils.GET_RESPONSE_FROM_URL_SUCCESS;
import static br.com.sasolucoes.yourmarket.network.ServerUtils.READ_TIMEOUT;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import br.com.sasolucoes.yourmarket.network.ServerUtils;

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

		if (!isNetworkConnected())
			Log.d(TAG, "Network unavaiable.");
		
		new GetFromURLConnectionTask().execute(getUrl());
	}

	private boolean isNetworkConnected() {
		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		
		return (networkInfo != null && networkInfo.isConnected());
	}

	private URL getUrl() {
		return ServerUtils.createUrl(ServerUtils.formatedAddress() + GET_COMPANY_ROUTE);
	}

	private class GetFromURLConnectionTask extends AsyncTask<URL, Void, String> {
		private InputStream stream = null;
		
		public InputStream getStreamResponse() {
			return stream;
		}

		/* 
		 * Thread process - where the job is done
		 */
		@Override
		protected String doInBackground(URL... urls) {
			try {
				return getResponseFromURL(urls[0]);
//				
//				if (!getResult.equals(GET_RESPONSE_FROM_URL_SUCCESS))
//					return getResult; 
//				
//				convertInputStreamToString(this.stream);
//				return getResult;
			} catch (IOException e) {
				Log.d(TAG, e.toString());
				return GET_RESPONSE_FROM_URL_FAIL;
			}
		}
		
		@Override
		protected void onPostExecute(String result) {
			// convert the InputStream
			if (!result.equals(GET_RESPONSE_FROM_URL_SUCCESS))
				return;
			
			String convertedStream = convertInputStreamToString(this.stream);
			
			// update database
		}


		private String getResponseFromURL(URL url) throws IOException {
			HttpURLConnection connection = null;
			
			try {
				connection = (HttpURLConnection)url.openConnection();
				connection.setReadTimeout(READ_TIMEOUT);
				connection.setConnectTimeout(CONNECT_TIMEOUT);
				connection.setRequestMethod(ServerUtils.REQUEST_METHOD);
				connection.setDoInput(true);
				connection.connect();
				
				int response = connection.getResponseCode();
				Log.d(TAG, "The response is: " + response);
				
				setStreamResponse(connection.getInputStream());
			} finally {
				if (connection != null)
					connection.disconnect();
			}
			return GET_RESPONSE_FROM_URL_SUCCESS;
		}

		private void setStreamResponse(InputStream stream) {
			this.stream = stream;
		}
		
		
		
		private String convertInputStreamToString(InputStream stream) {
			StringBuilder response = new StringBuilder();
			if (stream == null)
				return "";
			
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
			
			return response.toString();
		}
		
		private InputStream getCompanyDataFromServer() {
			Log.d(TAG, "getDataFromServer()");
			
			GetFromURLConnectionTask serverConnection = new GetFromURLConnectionTask();
			serverConnection.execute(getUrl());
			return serverConnection.getStreamResponse(); 
		}
	}
	
	
	
}
